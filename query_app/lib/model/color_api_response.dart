import 'dart:convert';

import 'package:query_app/model/crmk_color.dart';

import 'status.dart';

class ColorApiResponse {
  ColorApiResponse({required this.status, required this.payload});

  factory ColorApiResponse.fromJson(Map<String, dynamic> json) =>
      ColorApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkColor>.from(
              json['payload'].map((x) => CrmkColor.fromJson(x))));
  Status status;
  List<Object> payload;

  static ColorApiResponse apiResponseFromJson(String str) =>
      ColorApiResponse.fromJson(json.decode(str));
}
