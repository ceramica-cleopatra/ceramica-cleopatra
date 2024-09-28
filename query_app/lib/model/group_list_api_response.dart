import 'dart:convert';

import 'package:query_app/model/group_list.dart';

import 'crmk_types.dart';
import 'status.dart';

class GroupListApiResponse {
  GroupListApiResponse({required this.status, required this.payload});

  factory GroupListApiResponse.fromJson(Map<String, dynamic> json) =>
      GroupListApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<GroupList>.from(
              json['payload'].map((x) => GroupList.fromJson(x))));
  Status status;
  List<Object> payload;

  static GroupListApiResponse apiResponseFromJson(String str) =>
      GroupListApiResponse.fromJson(json.decode(str));
}
