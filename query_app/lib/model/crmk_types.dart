class CrmkTypes {

  CrmkTypes({required this.id, required this.name});

  factory CrmkTypes.fromJson(json) {
    return CrmkTypes(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
