class CrmkColor {
  CrmkColor({required this.id, required this.name});

  factory CrmkColor.fromJson(json) {
    return CrmkColor(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
