import 'package:flutter/material.dart';
import 'package:query_app/group.dart';
import 'package:query_app/model/group_list.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../crmk_search_result_table.dart';
import '../login_screen.dart';
import '../service/group_service.dart';

class NavDrawer extends StatelessWidget {
  String crmkDcre = '';
  String typeId = '';
  String sizeId = '';
  String factoryNo = '';
  String dekalaId = '';
  String frz = '';
  String tablow = '';
  String colorId = '';
  NavDrawer(String crmkDcre, String typeId, String sizeId, String factoryNo,
      String dekalaId, String frz, String tablow, String colorId) {
    this.crmkDcre = crmkDcre;
    this.typeId = typeId;
    this.sizeId = sizeId;
    this.factoryNo = factoryNo;
    this.dekalaId = dekalaId;
    this.frz = frz;
    this.tablow = tablow;
    this.colorId = colorId;
    _getGroupList();
    _getGovId();
  }
  List<GroupList> groupList = [];
  int govId = 0;
  Future<void> _getGovId() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    govId = prefs.getInt('govId') ?? 0;
  }

  @override
  void initState() {
    _getGroupList();
    _getGovId();
  }

  @override
  Widget build(BuildContext context) {
    return Drawer(
        backgroundColor: Color.fromARGB(255, 165, 154, 119),
        child: ListView(
            padding: EdgeInsets.symmetric(vertical: 30),
            children: <Widget>[
              ListTile(
                textColor: Colors.black,
                focusColor: Colors.black,
                iconColor: Colors.black,
                selectedColor: Colors.black,
                hoverColor: Colors.black,
                autofocus: true,
                contentPadding: EdgeInsets.symmetric(horizontal: 30.0),
                leading: Icon(Icons.account_tree_rounded),
                title: Text('المجموعــــات',
                    style: TextStyle(
                        color: Colors.black,
                        fontSize: 20,
                        fontWeight: FontWeight.bold)),
                onTap: () => {_route(context)},
              ),
              Divider(thickness: 1),
            ]));
  }

  _route(BuildContext context) async {
    if (groupList.length >= 1) {
      MaterialPageRoute materialPageRoute = MaterialPageRoute(
          builder: (BuildContext context) => Group(groupList: groupList));
      Navigator.push(context, materialPageRoute);
    }
  }

  Future<void> _getGroupList() async {
    try {
      groupList = await GroupService.getGroupList(
          crmkDcre, typeId, sizeId, dekalaId, factoryNo, frz, tablow, colorId);
    } catch (error) {
      print(error);
    }
  }
}
