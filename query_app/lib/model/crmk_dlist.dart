class CrmkDlist {
  CrmkDlist(
      {required this.id,
      required this.no,
      required this.trnsDate,
      required this.notes,
      required this.defaultValue});

  factory CrmkDlist.fromJson(json) {
    return CrmkDlist(
        id: json['id'] as int,
        no: json['no'] as int,
        trnsDate: json['trnsDate'] as String,
        notes: json['notes'] as String,
        defaultValue: json['defaultValue'] as String);
  }

  int id;
  int no;
  String trnsDate;
  String notes;
  String defaultValue;

  @override
  String toString() {
    return '{"id": $id,"no": $no, "trnsDate": "$trnsDate", "notes": "$notes", "defaultValue": "$defaultValue"}';
  }
}
