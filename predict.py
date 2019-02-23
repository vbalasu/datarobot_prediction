#!/usr/bin/env python
'''
USAGE: /udfs/trifacta_python /udfs/predict.py <API_TOKEN> <DEPLOYMENT_ID> <DATAROBOT_KEY> <USERNAME> <INPUT_FILE> 
See config.json.gpg for keys
'''
import sys
if len(sys.argv) != 6:
  print('Syntax: python predict.py <API_TOKEN> <DEPLOYMENT_ID> <DATAROBOT_KEY> <USERNAME> <INPUT_FILE>')
  exit()
API_TOKEN = sys.argv[1]
DEPLOYMENT_ID = sys.argv[2]
DATAROBOT_KEY = sys.argv[3]
USERNAME = sys.argv[4]
INPUT_FILE = sys.argv[5]
import os
filename = '/udfs/'+os.path.basename(INPUT_FILE)
os.system('rm '+filename)
os.system('hdfs dfs -copyToLocal {0} /udfs/'.format(INPUT_FILE))
# Usage: python datarobot-predict.py <input-file.csv>

# This example uses the requests library which you can install with:
#     pip install requests
# We highly recommend that you update SSL certificates with:
#     pip install -U urllib3[secure] certifi
import requests, sys
import json
# Set HTTP headers
# Note: The charset should match the contents of the file.
headers = {'Content-Type': 'text/plain; charset=UTF-8', 'datarobot-key': DATAROBOT_KEY}

data = open(filename, 'rb').read()

# Make predictions on your data
# The URL has the following format:
#     https://spa.orm.datarobot.com/predApi/v1.0/deployments/<DEPLOYMENT_ID>/predictions
# See docs for details:
#     app.datarobot.com/docs/users-guide/deploy/api/new-prediction-api.html
predictions_response = requests.post('https://spa.orm.datarobot.com/predApi/v1.0/deployments/%s/predictions' % (DEPLOYMENT_ID), auth=(USERNAME, API_TOKEN), data=data, headers=headers)

predictions_response.raise_for_status()
print(json.dumps(predictions_response.json()))

