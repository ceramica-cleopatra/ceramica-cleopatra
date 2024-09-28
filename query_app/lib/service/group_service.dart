import 'dart:convert';

import 'package:query_app/model/group_details_api_response.dart';
import 'package:query_app/model/group_details_result.dart';
import 'package:query_app/model/group_list.dart';
import 'package:http/http.dart' as http;
import '../model/group_list_api_response.dart';
import 'authorization_service.dart';

class GroupService {
  static Future<List<GroupList>> getGroupList(
      String crmkDcre,
      String typeId,
      String sizeId,
      String dekalaId,
      String factoryNo,
      String frz,
      String tablow,
      String colorId) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      String apiUrl = '';
      if (crmkDcre == 'C') {
        apiUrl =
            'http://197.44.140.11:8080/query/getCrmkGroups?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz';
      } else if (crmkDcre == 'D') {
        apiUrl =
            'http://197.44.140.11:8080/query/getDcreGroups?access_token=$accessToken&typeId=$typeId&sizeId=$sizeId&dekalaId=$dekalaId&factoryNo=$factoryNo&frz=$frz&tablow=$tablow&colorId=$colorId';
      }
      print(apiUrl);
      http.Response response = await http.get(Uri.parse(apiUrl));

      if (response.statusCode == 200) {
        final GroupListApiResponse apiResponse =
            GroupListApiResponse.apiResponseFromJson(response.body);

        final Iterable l = json.decode(apiResponse.payload.toString());
        List<GroupList> groupList =
            List<GroupList>.from(l.map((model) => GroupList.fromJson(model)));
        print(groupList);
        return groupList;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }

  static Future<List<GroupDetailsResult>> getGroupDetails(
      String bathId, String govId) async {
    try {
      String accessToken = await AuthorizationService.getAccessToken();
      final String apiUrl =
          'http://197.44.140.11:8080/query/getGroupDetails?access_token=$accessToken&bathId=$bathId&govId=$govId';
      http.Response response = await http.get(Uri.parse(apiUrl));
      print(apiUrl);
      if (response.statusCode == 200) {
        print(response.body.toString());
        final GroupDetailsApiResponse apiResponse =
            GroupDetailsApiResponse.apiResponseFromJson(response.body);

        final Iterable l = json.decode(apiResponse.payload.toString());
        List<GroupDetailsResult> groupDetails = List<GroupDetailsResult>.from(
            l.map((model) => GroupDetailsResult.fromJson(model)));
        print(groupDetails);
        return groupDetails;
      } else {
        throw 'Failed to load cases list';
      }
    } catch (e) {
      print(e);
      throw 'Failed to load cases list';
    }
  }
}
