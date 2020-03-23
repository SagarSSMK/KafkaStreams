package app.windows;

import config.Configuration;
import model.Key;
import model.windowsaggregartion.SessionAggregation;
import model.windowsaggregartion.SessionTest;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import serdes.StreamsSerdes;

public class WindowOperations {

    public static void main(String[] args) {
        StreamsConfig streamsConfig = new StreamsConfig(Configuration.getConfig());

        // Stream builder initialization to read from source topic
        StreamsBuilder builder = new StreamsBuilder();

        // key and value serde
        Serde<String> stringSerde = Serdes.String();
        Serde<Key>keySerde= StreamsSerdes.keySerde();
        Serde<SessionTest> sessionTestSerde= StreamsSerdes.sessionTestSerde();
        Serde<SessionAggregation> sessionAggregationSerde= StreamsSerdes.sessionAggregationSerde();


        KStream<Key, SessionTest> sessionStream = builder.stream("session",
                Consumed.with(keySerde, sessionTestSerde).withOffsetResetPolicy(Topology.AutoOffsetReset.EARLIEST));

        sessionStream.print(Printed.<Key,SessionTest>toSysOut().withLabel("Table"));


        /** #########################################################################################################################*/
                                                        //Session Windows

        /**
         * Here it it's given the inactivity time of 1second and retention time of 60 seconds...
         * Window checks th inactivity time in the arriving record and pushes to previous if it satisfies the condition otherwise creates
         * a new session.  it merges based on the function addAgg which is implemented from Merger interface within retention time.
         */

        /** #########################################################################################################################*/


        //Important thing here af Merging sessions
        long oneSecond = 1000 *60 ;
        long sixtySecond =  60*1000;

        KTable<Windowed<String>,SessionAggregation> sessionAggregationKTable =   sessionStream.groupBy((k, v)->v.getRegion()
                ,Serialized.with(stringSerde,sessionTestSerde))
                .windowedBy(org.apache.kafka.streams.kstream.SessionWindows.with(oneSecond).until(sixtySecond))
                .aggregate(SessionAggregation::new,(k,v,agg)->agg.add(v),(k,agg1,agg2)->agg1.addAgg(agg2)
                ,Materialized.with(stringSerde,sessionAggregationSerde));

        sessionAggregationKTable.toStream().print(Printed.<Windowed<String>, SessionAggregation>toSysOut().withLabel("Session Data"));

        /** #########################################################################################################################*/

        /** #########################################################################################################################*/
                                                            //Tumbling Windows

        /**
         * Here 10 second has been given as time window, basically it calculate over 10 seconds and goes for the next session which continues...
         */
        /** #########################################################################################################################*/

/*

        long tenSecond = 1000*10;

        KTable<Windowed<String>,SessionAggregation> sessionTumblingAggregationKTable =   sessionStream.groupBy((k, v)->v.getRegion()
                ,Serialized.with(stringSerde,sessionTestSerde))
                .windowedBy(TimeWindows.of(tenSecond))
                .aggregate(SessionAggregation::new,(k,v,agg)->agg.add(v)
                        ,Materialized.with(stringSerde,sessionAggregationSerde));

        sessionTumblingAggregationKTable.toStream().print(Printed.<Windowed<String>, SessionAggregation>toSysOut().withLabel("Tumbling Data"));
*/

        /** #########################################################################################################################*/


        /** #########################################################################################################################*/
                                                              //Sliding Window
        /**
         * It takes a period of 10ms to calculate the aggregation and it pushes the window every 1ms in here, it updates the values continuously
         * and you can observe the number of updates in(window time/sliding window time)
         */
        /** #########################################################################################################################*/



     /*   long slidingWindowTime=10;
        long waitinTime= 30;//
        KTable<Windowed<String>,SessionAggregation> sessionAggSlidingWindow = sessionStream.groupBy((k,v)->v.getRegion(),
                Serialized.with(stringSerde,sessionTestSerde))
                .windowedBy(TimeWindows.of(slidingWindowTime).advanceBy(1).until(150000))
                .aggregate(SessionAggregation::new,(k,v,agg)->agg.add(v)
                ,Materialized.with(stringSerde,sessionAggregationSerde));

        sessionAggSlidingWindow.toStream().print(Printed.<Windowed<String>,SessionAggregation>toSysOut().withLabel("Sliding Window"));
*/
        /** #########################################################################################################################*/

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfig);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                kafkaStreams.close();
            }
        }));
    }


}

