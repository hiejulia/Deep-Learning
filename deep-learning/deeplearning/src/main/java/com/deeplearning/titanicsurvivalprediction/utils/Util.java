package com.deeplearning.titanicsurvivalprediction.utils;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

import java.io.Serializable;

import com.deeplearning.titanicsurvivalprediction.SparkConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;


public class Util {

    public static class VectorPair implements Serializable {
        private double label;
        private Vector features;

        public VectorPair(double label, Vector features) {
            this.label = label;
            this.features = features;
        }

        public VectorPair() {}

        public void setFeatures(Vector features) {
            this.features = features;
        }

        public Vector getFeatures() {
            return this.features;
        }

        public void setLable(double label) {
            this.label = label;
        }

        public double getLable() {
            return this.label;
        }
    }

    public static Tuple3<Double, Double, Double> flattenPclass(double value) {
        Tuple3<Double, Double, Double> result;

        if (value == 1)
            result = new Tuple3<>(1d, 0d, 0d);
        else if (value == 2)
            result = new Tuple3<>(0d, 1d, 0d);
        else
            result =new Tuple3<>(0d, 0d, 1d);

        return result;
    }

    public static Tuple3<Double, Double, Double> flattenEmbarked(double value) {
        Tuple3<Double, Double, Double> result;

        if (value == 0)
            result = new Tuple3<>(1d, 0d, 0d);
        else if (value == 1)
            result = new Tuple3<>(0d, 1d, 0d);
        else
            result = new Tuple3<>(0d, 0d, 1d);

        return result;
    }

    public static Tuple2<Double, Double> flattenSex(double value) {
        Tuple2<Double, Double> result;

        if (value == 0)
            result = new Tuple2<>(1d, 0d);
        else
            result = new Tuple2<>(0d, 1d);

        return result;
    }

    public static Double scaleValue(double min, double max, double value) {
        return (value - min) / max - min;
    }

    public static org.apache.spark.mllib.linalg.Vector getScaledVector(double fare, double age, double pclass,
                                                                       double sex, double embarked, StandardScalerModel scaler) {
        org.apache.spark.mllib.linalg.Vector scaledContinous = scaler.transform(Vectors.dense(fare, age));
        Tuple3<Double, Double, Double> pclassFlat = flattenPclass(pclass);
        Tuple3<Double, Double, Double> embarkedFlat = flattenEmbarked(embarked);
        Tuple2<Double, Double> sexFlat = flattenSex(sex);

        return Vectors.dense(
                scaledContinous.apply(0),
                scaledContinous.apply(1),
                sexFlat._1(),
                sexFlat._2(),
                pclassFlat._1(),
                pclassFlat._2(),
                pclassFlat._3(),
                embarkedFlat._1(),
                embarkedFlat._2(),
                embarkedFlat._3());
    }

    public static UDF1<String, Option<Integer>> normSex = (String d) -> {
        if (null == d)
            return Option.apply(null);
        else {
            if (d.equals("male"))
                return Some.apply(0);
            else
                return Some.apply(1);
        }
    };

    public static UDF1<String, Option<Integer>> normEmbarked = (String d) -> {
        if (null == d)
            return Option.apply(null);
        else {
            if (d.equals("S"))
                return Some.apply(0);
            else if (d.equals("C"))
                return Some.apply(1);
            else
                return Some.apply(2);
        }
    };

    public static MultivariateStatisticalSummary summary = null;

    public static SparkSession spark = SparkConfig.getInstance();

    public static Dataset<Row> getTrainingDF() {
        spark.sqlContext().udf().register("normSex", normSex, DataTypes.IntegerType);
        spark.sqlContext().udf().register("normEmbarked", normEmbarked, DataTypes.IntegerType);

        Dataset<Row> df = spark.sqlContext()
                .read()
                .format("com.databricks.spark.csv")
                .option("header", "true") // Use first line of all files as header
                .option("inferSchema", "true") // Automatically infer data types
                .load("data/train.csv");

        Dataset<Row> projection = df.select(
                col("Survived"),
                col("Fare"),
                callUDF("normSex", col("Sex")).alias("Sex"),
                col("Age"),
                col("Pclass"),
                col("Parch"),
                col("SibSp"),
                callUDF("normEmbarked", col("Embarked")).alias("Embarked"));

        JavaRDD<Vector> statsDf = projection.rdd().toJavaRDD().map(row -> Vectors.dense(
                row.<Double>getAs("Fare"),
                row.isNullAt(3) ? 0d : row.<Double>getAs("Age")));

        summary = Statistics.colStats(statsDf.rdd());

        double meanFare = summary.mean().apply(0);
        double meanAge = summary.mean().apply(1);

        // Define udfs, which fill in mean values, if the data has no entry
        UDF1<String, Option<Double>> normFare = (String d) -> {
            if (null == d) {
                return Some.apply(meanFare);
            }
            else
                return Some.apply(Double.parseDouble(d));
        };

        UDF1<String, Option<Double>> normAge = (String d) -> {
            if (null == d)
                return Some.apply(meanAge);
            else
                return Some.apply(Double.parseDouble(d));
        };

        spark.sqlContext().udf().register("normFare", normFare, DataTypes.DoubleType);
        spark.sqlContext().udf().register("normAge", normAge, DataTypes.DoubleType);

        // select the columns we need and apply the udfs

        Dataset<Row> finalDF = projection.select(
                col("Survived"),
                callUDF("normFare", col("Fare").cast("string")).alias("Fare"),
                col("Sex"),
                callUDF("normAge", col("Age").cast("string")).alias("Age"),
                col("Pclass"),
                col("Parch"),
                col("SibSp"),
                col("Embarked"));
        finalDF.show();

        return finalDF;
    }

    public static Dataset<Row> getTestDF() {
        spark.sqlContext().udf().register("normSex", normSex, DataTypes.IntegerType);
        spark.sqlContext().udf().register("normEmbarked", normEmbarked, DataTypes.IntegerType);

        // load the Test data
        Dataset<Row> testDf = spark.sqlContext()
                .read()
                .format("com.databricks.spark.csv")
                .option("header", "true") // Use first line of all files as header
                .option("inferSchema", "true") // Automatically infer data types
                .load("data/test.csv");

        return testDf.select(
                col("PassengerId"),
                col("Fare"),
                callUDF("normSex", col("Sex")).alias("Sex"),
                col("Age"),
                col("Pclass"),
                col("Parch"),
                col("SibSp"),
                callUDF("normEmbarked", col("Embarked")).alias("Embarked"));
    }
}
