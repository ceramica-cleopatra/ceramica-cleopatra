import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:query_app/model/sehy_names.dart';
import 'package:query_app/model/sehy_takm_detail_api_response.dart';

import '../model/names_api_response.dart';
import '../model/sehy_dekala_api_response.dart';
import '../model/sehy_dekalas.dart';
import '../model/sehy_govern.dart';
import '../model/sehy_search_api_response.dart';
import '../model/sehy_search_result.dart';
import '../model/sehy_types.dart';
import '../model/sehy_types_api_response.dart';
import 'authorization_service.dart';

class SehyService {
  static Future<List<SehyTypes>> getSehyTypes(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl = '';
      if (id == null || id.isEmpty) {
        apiUrl =
            'http://197.44.140.11:8080/query/getSehyTypes?access_token=$accessToken&name=$name';
      } else {
        apiUrl =
            'http://197.44.140.11:8080/query/getSehyTypes?access_token=$accessToken&id=$id';
      }
      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final SehyTypesApiResponse apiResponse =
            SehyTypesApiResponse.apiResponseFromJson(response.body);

        final Iterable l = json.decode(apiResponse.payload.toString());
        List<SehyTypes> sehyTypesList =
            List<SehyTypes>.from(l.map((model) => SehyTypes.fromJson(model)));
        return sehyTypesList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<SehyNames>> getSehyNames(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getSehyNames?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final NamesApiResponse apiResponse =
            NamesApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<SehyNames> sehyNameList =
            List<SehyNames>.from(l.map((model) => SehyNames.fromJson(model)));
        return sehyNameList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<SehyDekalas>> getSehyDekalas(
      String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      name = name.toUpperCase();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getSehyDekalas?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final SehyDekalaApiResponse apiResponse =
            SehyDekalaApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<SehyDekalas> sehyDekalaList = List<SehyDekalas>.from(
            l.map((model) => SehyDekalas.fromJson(model)));
        return sehyDekalaList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<SehyGovern> getSehyTakmDetails(String typeId, String nameId,
      String dekalaId, String colorId, String frz) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getSehyTakmDetails?access_token=$accessToken&typeId=$typeId&nameId=$nameId&dekalaId=$dekalaId&colorId=$colorId&frz=$frz';
      print(
          'http://197.44.140.11:8080/query/getSehyTakmDetails?access_token=$accessToken&typeId=$typeId&nameId=$nameId&dekalaId=$dekalaId&colorId=$colorId&frz=$frz');
      final http.Response response = await http.get(Uri.parse(apiUrl));
      print(response.statusCode);
      if (response.statusCode == 200) {
        final SehyTakmDetailApiResponse apiResponse =
            SehyTakmDetailApiResponse.apiResponseFromJson(response.body);
        final l = json.decode(apiResponse.payload.toString());
        SehyGovern sehyGovern = SehyGovern.fromJson(l);
        return sehyGovern;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<SehySearchResult>> getSehySearchResult(
      String typeId,
      String typeName,
      String nameId,
      String name,
      String dekalaId,
      String dekalaName,
      String colorId,
      String colorName,
      String frz,
      String page,
      String perPage) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getSehySearch?access_token=$accessToken&typeId=$typeId&typeName=$typeName&nameId=$nameId&name=$name&dekalaId=$dekalaId&dekalaName=$dekalaName&colorId=$colorId&colorName=$colorName&frz=$frz&page=$page&perPage=$perPage';
      print(
          'http://197.44.140.11:8080/query/getSehySearch?access_token=$accessToken&typeId=$typeId&typeName=$typeName&nameId=$nameId&name=$name&dekalaId=$dekalaId&dekalaName=$dekalaName&colorId=$colorId&colorName=$colorName&frz=$frz&page=$page&perPage=$perPage');
      final http.Response response = await http.get(Uri.parse(apiUrl));
      print('typeId>>>' + typeId + "typeName" + typeName);
      if (response.statusCode == 200) {
        final SehySearchApiResponse apiResponse =
            SehySearchApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<SehySearchResult> sehySearchResultList =
            List<SehySearchResult>.from(
                l.map((model) => SehySearchResult.fromJson(model)));
        return sehySearchResultList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }
}
