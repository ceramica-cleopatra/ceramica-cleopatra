class DcreDekalas {
  DcreDekalas({required this.id, required this.name});

  factory DcreDekalas.fromJson(json) {
    return DcreDekalas(id: json['id'] as String, name: json['name'] as String);
  }
  String id;
  String name;

  @override
  String toString() {
    return '{"id": "$id", "name": "$name"}';
  }
}
