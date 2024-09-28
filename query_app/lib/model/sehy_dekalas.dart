class SehyDekalas {
  SehyDekalas({required this.id, required this.name});

  factory SehyDekalas.fromJson(json) {
    return SehyDekalas(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
