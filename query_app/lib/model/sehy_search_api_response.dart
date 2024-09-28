import 'dart:convert';

import 'package:query_app/model/sehy_search_result.dart';

import 'crmk_search_result.dart';
import 'status.dart';

class SehySearchApiResponse {
  SehySearchApiResponse({required this.status, required this.payload});

  factory SehySearchApiResponse.fromJson(Map<String, dynamic> json) =>
      SehySearchApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<SehySearchResult>.from(
              json['payload'].map((x) => SehySearchResult.fromJson(x))));
  Status status;
  List<Object> payload;

  static SehySearchApiResponse apiResponseFromJson(String str) =>
      SehySearchApiResponse.fromJson(json.decode(str));
}
