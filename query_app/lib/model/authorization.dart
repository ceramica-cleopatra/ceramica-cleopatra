// ignore_for_file: non_constant_identifier_names

import 'dart:convert';

class Authorization {
  String access_token;
  String token_type;
  int expires_in;
  String scope;

  Authorization(
      {required this.access_token,
      required this.token_type,
      required this.expires_in,
      required this.scope});

  factory Authorization.fromJson(json) {
    return Authorization(
        access_token: json['access_token'] as String,
        token_type: json['token_type'] as String,
        expires_in: json['expires_in'] as int,
        scope: json['scope'] as String);
  }

  static Authorization apiResponseFromJson(String str) =>
      Authorization.fromJson(json.decode(str));

  @override
  String toString() {
    return '{"access_token": "$access_token", "token_type": "$token_type", "expires_in": $expires_in, "scope": "$scope"}';
  }
}
