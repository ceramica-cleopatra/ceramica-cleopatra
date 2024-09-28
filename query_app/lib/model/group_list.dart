class GroupList {
  GroupList(
      {required this.bathName, required this.bathColor, required this.bathId});

  factory GroupList.fromJson(json) {
    return GroupList(
        bathName: json['bathName'] as String,
        bathColor: json['bathColor'] as String,
        bathId: json['bathId'] as int);
  }
  String bathName;
  String bathColor;
  int bathId;
  @override
  String toString() {
    return '{"bathName": "$bathName", "bathColor": "$bathColor", "bathId": $bathId}';
  }
}
