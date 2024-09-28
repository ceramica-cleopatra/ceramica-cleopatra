import 'dart:convert';

import 'package:query_app/model/authorization.dart';
import 'package:http/http.dart' as http;

class AuthorizationService {
  static Future<String> getAccessToken() async {
    try {
      String apiUrl =
          'http://197.44.140.11:8091/oauth/token?grant_type=password&username=ccg&password=P@\$\$w0rd';
      print(apiUrl);

      http.Response response = await http.post(Uri.parse(apiUrl),
          headers: <String, String>{
            'authorization':
                'Basic ' + base64.encode(utf8.encode('ccg-client:ccg-secret'))
          });

      if (response.statusCode == 200) {
        final Authorization apiResponse =
            Authorization.apiResponseFromJson(response.body);
        print(apiResponse);
        return apiResponse.access_token;
      } else {
        throw 'AuthorizationService Failed to load cases list' +
            response.statusCode.toString();
      }
    } catch (e) {
      print(e);
      throw 'AuthorizationService 2 Failed to load cases list';
    }
  }
}
