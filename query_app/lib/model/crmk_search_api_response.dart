import 'dart:convert';

import 'crmk_search_result.dart';
import 'status.dart';

class CrmkSearchApiResponse {

  CrmkSearchApiResponse({ required this.status,  required this.payload});

  factory CrmkSearchApiResponse.fromJson(Map<String, dynamic> json) =>
      CrmkSearchApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkSearchResult>.from(
              json['payload'].map((x) => CrmkSearchResult.fromJson(x))));
  Status status;
  List<Object> payload;

  static CrmkSearchApiResponse apiResponseFromJson(String str) =>
      CrmkSearchApiResponse.fromJson(json.decode(str));
}
