/**
 * Trifacta Inc. Confidential
 *
 * Copyright 2015 Trifacta Inc.
 * All Rights Reserved.
 *
 * Any use of this material is subject to the Trifacta Inc., Source License located
 * in the file 'SOURCE_LICENSE.txt' which is part of this package.  All rights to
 * this material and any derivative works thereof are reserved by Trifacta Inc.
 */
/**
 * Trifacta Inc. Confidential
 *
 * Copyright 2015 Trifacta Inc.
 * All Rights Reserved.
 *
 * Any use of this material is subject to the Trifacta Inc., Source License located
 * in the file 'SOURCE_LICENSE.txt' which is part of this package.  All rights to
 * this material and any derivative works thereof are reserved by Trifacta Inc.
 */

package com.trifacta.trifactaudfs;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author or
 * UDF that calls the DataRobot prediction API
 */
public class DatarobotPredict implements TrifactaUDF<String> {

  private String _API_TOKEN;
  private String _DEPLOYMENT_ID;
  private String _DATAROBOT_KEY;
  private String _USERNAME;
  private String _INPUT_FILE;
  private String _OUTPUT;
  private boolean _error;


  @Override
  public String exec(List<Object> inputs) throws IOException {
    if (inputs == null) {
      return null;
    }
    _INPUT_FILE = inputs.get(0).toString();
    DatarobotAPI d = new DatarobotAPI();
    if(!_error) _OUTPUT = d.call(_API_TOKEN, _DEPLOYMENT_ID, _DATAROBOT_KEY, _USERNAME, _INPUT_FILE);
    return _OUTPUT;
  }

  @SuppressWarnings("rawtypes")
  public Class[] inputSchema() {
    return new Class[]{String.class};
  }

  @Override
  public void finish() throws IOException {
  }

  @Override
  public void init(List<Object> initArgs) {
    int numArgs = initArgs.size();
    if (numArgs != 1) {
      _OUTPUT = "Arguments expected: API_TOKEN=YOUR_API_TOKEN,DEPLOYMENT_ID=YOUR_DEPLOYMENT_ID,DATAROBOT_KEY=YOUR_DATAROBOT_KEY,USERNAME=YOUR_USERNAME";
      _error = true;
    }
    else {
      //String paramstring = "API_TOKEN=AlvuLGmtQOYkFt1temri5i4Kc4QcwRi8,DEPLOYMENT_ID=5c37fa5c11a57d006e647adb,DATAROBOT_KEY=2b9119cd-7945-bf9d-8397-1b6abd602f7e,USERNAME=vijay@trifacta.com";
      String paramstring = initArgs.get(0).toString();
      String[] params = paramstring.split(",");
      _API_TOKEN = params[0].split("=")[1];
      _DEPLOYMENT_ID = params[1].split("=")[1];
      _DATAROBOT_KEY = params[2].split("=")[1];
      _USERNAME = params[3].split("=")[1];
    }
  }
}
