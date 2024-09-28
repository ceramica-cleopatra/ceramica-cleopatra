import 'dart:convert';

import 'crmk_sizes.dart';
import 'status.dart';

class SizeApiResponse {

  SizeApiResponse({required this.status, required this.payload});

  factory SizeApiResponse.fromJson(Map<String, dynamic> json) =>
      SizeApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkSizes>.from(
              json['payload'].map((x) => CrmkSizes.fromJson(x))));
  Status status;
  List<Object> payload;

  static SizeApiResponse apiResponseFromJson(String str) =>
      SizeApiResponse.fromJson(json.decode(str));
}
