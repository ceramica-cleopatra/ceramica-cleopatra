class SehyNames {
  SehyNames({required this.id, required this.name});

  factory SehyNames.fromJson(json) {
    return SehyNames(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
