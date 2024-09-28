import 'dart:convert';

import 'package:query_app/model/price.dart';
import 'package:http/http.dart' as http;
import 'package:query_app/model/price_response.dart';

import 'authorization_service.dart';

class PriceService {
  static Future<Price> getCrmkPrice(
      String typeId,
      String sizeId,
      String dekalaId,
      String factoryNo,
      String frz,
      String pList,
      String dList) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkPrice?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz&pList=$pList&dList=$dList';

      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final PriceResponse apiResponse =
            PriceResponse.apiResponseFromJson(response.body);
        return Price.fromJson(json.decode(apiResponse.payload.toString()));
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<Price> getDcrePrice(
      String typeId,
      String sizeId,
      String dekalaId,
      String factoryNo,
      String frz,
      String colorId,
      String tablow,
      String pList,
      String dList) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl =
          'http://197.44.140.11:8080/query/getDcrePrice?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz&colorId=$colorId&tablow=$tablow&pList=$pList&dList=$dList';

      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final PriceResponse apiResponse =
            PriceResponse.apiResponseFromJson(response.body);
        return Price.fromJson(json.decode(apiResponse.payload.toString()));
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }
}
