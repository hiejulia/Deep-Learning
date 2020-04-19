package com.deeplearning.titanicsurvivalprediction;

import org.apache.spark.sql.SparkSession;

public final class SparkConfig {
    private static volatile SparkSession INSTANCE;

    public static SparkSession getInstance() {
        if (null == INSTANCE) {
            synchronized (SparkConfig.class) {
                if (null == INSTANCE)
                    INSTANCE = SparkSession
                            .builder()
                            .master("local[*]")
                            .config("spark.sql.warehouse.dir", "/tmp/spark")
                            .appName("SurvivalPredictionMLP")
                            .getOrCreate();
            }
        }

        return INSTANCE;
    }
}
