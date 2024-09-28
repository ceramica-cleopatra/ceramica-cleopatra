import 'dart:convert';

import 'package:query_app/model/group_details_result.dart';
import 'package:query_app/model/group_list.dart';

import '../group_details.dart';
import 'crmk_types.dart';
import 'status.dart';

class GroupDetailsApiResponse {
  GroupDetailsApiResponse({required this.status, required this.payload});

  factory GroupDetailsApiResponse.fromJson(Map<String, dynamic> json) =>
      GroupDetailsApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<GroupDetailsResult>.from(
              json['payload'].map((x) => GroupDetailsResult.fromJson(x))));
  Status status;
  List<Object> payload;

  static GroupDetailsApiResponse apiResponseFromJson(String str) =>
      GroupDetailsApiResponse.fromJson(json.decode(str));
}
