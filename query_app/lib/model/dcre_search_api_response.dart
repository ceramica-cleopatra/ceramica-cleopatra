import 'dart:convert';

import 'dcre_search_result.dart';
import 'status.dart';

class DcreSearchApiResponse {
  DcreSearchApiResponse({required this.status, required this.payload});

  factory DcreSearchApiResponse.fromJson(Map<String, dynamic> json) =>
      DcreSearchApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<DcreSearchResult>.from(
              json['payload'].map((x) => DcreSearchResult.fromJson(x))));
  Status status;
  List<Object> payload;

  static DcreSearchApiResponse apiResponseFromJson(String str) =>
      DcreSearchApiResponse.fromJson(json.decode(str));
}
