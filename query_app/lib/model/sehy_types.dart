class SehyTypes {
  SehyTypes({required this.id, required this.name});

  factory SehyTypes.fromJson(json) {
    return SehyTypes(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
