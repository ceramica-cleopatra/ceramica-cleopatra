import 'dart:convert';

import 'crmk_types.dart';
import 'status.dart';

class TypesApiResponse {

  TypesApiResponse({required this.status, required this.payload});

  factory TypesApiResponse.fromJson(Map<String, dynamic> json) =>
      TypesApiResponse(
          status: Status.fromJson(json['status']),
          payload: List<CrmkTypes>.from(
              json['payload'].map((x) => CrmkTypes.fromJson(x))));
  Status status;
  List<Object> payload;

  static TypesApiResponse apiResponseFromJson(String str) =>
      TypesApiResponse.fromJson(json.decode(str));
}
