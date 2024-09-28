import 'package:http/http.dart' as http;

class LoginService {
  static Future<int> validateUser(String usercode, String password) async {
    try {
      String apiUrl =
          'http://197.44.140.11:8088/validateUser?usercode=$usercode&password=$password';
      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final validationResult = response.body;
        return int.parse(validationResult);
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }
}
