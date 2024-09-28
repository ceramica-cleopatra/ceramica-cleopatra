class DcreTypes {
  DcreTypes({required this.id, required this.name});

  factory DcreTypes.fromJson(json) {
    return DcreTypes(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
