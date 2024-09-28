import 'dart:convert';

import 'package:http/http.dart' as http;

import '../model/crmk_detail_api_response.dart';
import '../model/crmk_dlist.dart';
import '../model/crmk_govern.dart';
import '../model/crmk_plist.dart';
import '../model/crmk_plist_response.dart';
import '../model/dcre_dekalas.dart';
import '../model/dcre_detail_api_response.dart';
import '../model/dcre_search_api_response.dart';
import '../model/dcre_search_result.dart';
import '../model/dcre_sizes.dart';
import '../model/dcre_types.dart';
import '../model/dekala_api_response.dart';
import '../model/size_api_response.dart';
import '../model/types_api_response.dart';
import 'authorization_service.dart';

class DcreService {
  static Future<List<DcreTypes>> getDcreTypes(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl = '';
      if (id == null || id.isEmpty) {
        apiUrl =
            'http://197.44.140.11:8080/query/getDcreTypes?access_token=$accessToken&name=$name';
      } else {
        apiUrl =
            'http://197.44.140.11:8080/query/getDcreTypes?access_token=$accessToken&id=$id';
      }

      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final TypesApiResponse apiResponse =
            TypesApiResponse.apiResponseFromJson(response.body);

        final Iterable l = json.decode(apiResponse.payload.toString());
        List<DcreTypes> dcreTypesList =
            List<DcreTypes>.from(l.map((model) => DcreTypes.fromJson(model)));
        return dcreTypesList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<DcreSizes>> getDcreSizes(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcreSizes?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final SizeApiResponse apiResponse =
            SizeApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<DcreSizes> dcreSizeList =
            List<DcreSizes>.from(l.map((model) => DcreSizes.fromJson(model)));
        return dcreSizeList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<DcreDekalas>> getDcreDekalas(
      String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      name = name.toUpperCase();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcreDekala?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final DekalaApiResponse apiResponse =
            DekalaApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<DcreDekalas> dcreDekalaList = List<DcreDekalas>.from(
            l.map((model) => DcreDekalas.fromJson(model)));
        return dcreDekalaList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<DcreSearchResult>> getDcreSearchResult(
      String typeId,
      String typeName,
      String sizeId,
      String sizeName,
      String dekalaId,
      String dekalaName,
      String factoryNo,
      String frz,
      String colorId,
      String colorName,
      String tablow,
      String page,
      String perPage) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcreSearch?access_token=$accessToken&typeId=$typeId&typeName=$typeName&sizeId=$sizeId&sizeName=$sizeName&dekalaId=$dekalaId&dekalaName=$dekalaName&factoryNo=$factoryNo&frz=$frz&page=$page&perPage=$perPage&colorId=$colorId&colorName=$colorName&tablow=$tablow';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final DcreSearchApiResponse apiResponse =
            DcreSearchApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<DcreSearchResult> dcreSearchResultList =
            List<DcreSearchResult>.from(
                l.map((model) => DcreSearchResult.fromJson(model)));
        return dcreSearchResultList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkGovern>> getDcreDetails(
      String typeId,
      String sizeId,
      String dekalaId,
      String factoryNo,
      String frz,
      String govId,
      String colorId,
      String tablow) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcreDetails?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz&govId=$govId&colorId=$colorId&tablow=$tablow';
      final http.Response response = await http.get(Uri.parse(apiUrl));
      print(response.statusCode);
      if (response.statusCode == 200) {
        print(response.body);
        final DcreDetailApiResponse apiResponse =
            DcreDetailApiResponse.apiResponseFromJson(response.body);
        print(apiResponse);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkGovern> crmkGovernList =
            List<CrmkGovern>.from(l.map((model) => CrmkGovern.fromJson(model)));
        return crmkGovernList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkPlist>> getDcrePlist() async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcrePlist?access_token=$accessToken';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final CrmkPlistApiResponse apiResponse =
            CrmkPlistApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkPlist> crmkPlist =
            List<CrmkPlist>.from(l.map((model) => CrmkPlist.fromJson(model)));
        return crmkPlist;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkDlist>> getDcreDlist() async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getDcreDlist?access_token=$accessToken';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final CrmkPlistApiResponse apiResponse =
            CrmkPlistApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkDlist> crmkDlist =
            List<CrmkDlist>.from(l.map((model) => CrmkDlist.fromJson(model)));
        return crmkDlist;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }
}
