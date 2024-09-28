class CrmkDekalas {

  CrmkDekalas({required this.id, required this.name});

  factory CrmkDekalas.fromJson(json) {
    return CrmkDekalas(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
