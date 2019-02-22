if [ "$(hostname)" = "VijayB-MacBook-Pro-43244.local" ]; then
  echo "running local steps"
  cp /Users/vbalasubramaniam/udfs/DatarobotPredict/out/artifacts/DatarobotPredict_jar/DatarobotPredict.jar .
  git add DatarobotPredict.jar
  git commit -m "Updated DatarobotPredict.jar"
  git push
else
  echo "running remote steps"
  git pull
  chown trifacta:trifacta DatarobotPredict.jar
  cp DatarobotPredict.jar /udfs/
  ln -s /usr/bin/python3 /udfs/trifacta_python
  ln -s /root/bitbucket/datarobot_prediction/predict.py /udfs/predict.py
  service trifacta restart
fi
