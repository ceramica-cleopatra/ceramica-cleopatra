import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:query_app/model/crmk_color.dart';
import 'package:query_app/service/authorization_service.dart';

import '../model/color_api_response.dart';
import '../model/crmk_dekalas.dart';
import '../model/crmk_detail_api_response.dart';
import '../model/crmk_dlist.dart';
import '../model/crmk_govern.dart';
import '../model/crmk_plist.dart';
import '../model/crmk_plist_response.dart';
import '../model/crmk_search_api_response.dart';
import '../model/crmk_search_result.dart';
import '../model/crmk_sizes.dart';
import '../model/crmk_types.dart';
import '../model/dekala_api_response.dart';
import '../model/size_api_response.dart';
import '../model/types_api_response.dart';

class CrmkService {
  static Future<List<CrmkTypes>> getCrmkTypes(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl = '';
      if (id == null || id.isEmpty) {
        apiUrl =
            'http://197.44.140.11:8080/query/getCrmkTypes?access_token=$accessToken&name=$name';
      } else {
        apiUrl =
            'http://197.44.140.11:8080/query/getCrmkTypes?access_token=$accessToken&id=$id';
      }
      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final TypesApiResponse apiResponse =
            TypesApiResponse.apiResponseFromJson(response.body);

        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkTypes> crmkTypesList =
            List<CrmkTypes>.from(l.map((model) => CrmkTypes.fromJson(model)));
        return crmkTypesList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkSizes>> getCrmkSizes(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkSizes?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final SizeApiResponse apiResponse =
            SizeApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkSizes> crmkSizeList =
            List<CrmkSizes>.from(l.map((model) => CrmkSizes.fromJson(model)));
        return crmkSizeList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkDekalas>> getCrmkDekalas(
      String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      name = name.toUpperCase();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkDekala?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final DekalaApiResponse apiResponse =
            DekalaApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkDekalas> crmkDekalaList = List<CrmkDekalas>.from(
            l.map((model) => CrmkDekalas.fromJson(model)));
        return crmkDekalaList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkColor>> getCrmkColors(String id, String name) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkColors?access_token=$accessToken&id=$id&name=$name';
      final http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final ColorApiResponse apiResponse =
            ColorApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());
        List<CrmkColor> crmkColorList =
            List<CrmkColor>.from(l.map((model) => CrmkColor.fromJson(model)));
        return crmkColorList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkSearchResult>> getCrmkSearchResult(
      String typeId,
      String typeName,
      String sizeId,
      String sizeName,
      String dekalaId,
      String dekalaName,
      String factoryNo,
      String frz,
      String page,
      String perPage) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkSearch?access_token=$accessToken&typeId=$typeId&typeName=$typeName&sizeId=$sizeId&sizeName=$sizeName&dekalaId=$dekalaId&dekalaName=$dekalaName&factoryNo=$factoryNo&frz=$frz&page=$page&perPage=$perPage';
      final http.Response response = await http.get(Uri.parse(apiUrl));
      if (response.statusCode == 200) {
        final CrmkSearchApiResponse apiResponse =
            CrmkSearchApiResponse.apiResponseFromJson(response.body);
        final Iterable l = json.decode(apiResponse.payload.toString());

        List<CrmkSearchResult> crmkSearchResultList =
            List<CrmkSearchResult>.from(
                l.map((model) => CrmkSearchResult.fromJson(model)));
        return crmkSearchResultList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<CrmkGovern>> getCrmkDetails(String typeId, String sizeId,
      String dekalaId, String factoryNo, String frz, String govId) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/crmkSearchDetails?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz&govId=$govId';
      final http.Response response = await http.get(Uri.parse(apiUrl));
      if (response.statusCode == 200) {
        final CrmkDetailApiResponse apiResponse =
            CrmkDetailApiResponse.apiResponseFromJson(response.body);
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

  static Future<List<CrmkPlist>> getCrmkPlist() async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkPriceList?access_token=$accessToken';
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

  static Future<List<CrmkDlist>> getCrmkDlist() async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getCrmkDiscountList?access_token=$accessToken';
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
