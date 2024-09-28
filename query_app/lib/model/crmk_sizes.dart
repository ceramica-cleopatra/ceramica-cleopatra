class CrmkSizes {

  CrmkSizes({required this.id, required this.name});

  factory CrmkSizes.fromJson(json) {
    return CrmkSizes(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
