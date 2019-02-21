scp /Users/vbalasubramaniam/udfs/DatarobotPredict/out/artifacts/DatarobotPredict_jar/DatarobotPredict.jar partnerdemo.trifacta.net:/home/vbalasubramaniam/
ssh partnerdemo.trifacta.net sudo chown trifacta:trifacta DatarobotPredict.jar
ssh partnerdemo.trifacta.net sudo mv DatarobotPredict.jar /opt/trifacta/
ssh partnerdemo.trifacta.net sudo service trifacta restart

