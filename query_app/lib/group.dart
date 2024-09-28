import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:provider/provider.dart';
import 'package:query_app/group_details.dart';
import 'package:query_app/model/group_details_result.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:query_app/service/group_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'crmk_search_result_table.dart';
import 'login_screen.dart';
import 'model/group_list.dart';

class Group extends StatefulWidget {
  Group({Key? key, required this.groupList}) : super(key: key);
  List<GroupList> groupList;
  _GroupState createState() => _GroupState();
}

class _GroupState extends State<Group> {
  _GroupState();

  @override
  void initState() {
    super.initState();
    validateLoginStatus();
  }

  List<GroupDetailsResult> groupDetails = [];
  int govId = 0;
  Future<void> validateLoginStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      govId = prefs.getInt('govId') ?? 0;
    });
    String loginTime = prefs.getString('loginTime') ?? "2023-01-01 12:00:00";
    int hours = DateTime.now().difference(DateTime.parse(loginTime)).inHours;
    if (govId == 0 || hours >= 1) {
      MaterialPageRoute materialPageRoute =
          MaterialPageRoute(builder: (BuildContext context) => const Login());
      Navigator.push(context, materialPageRoute);
    }
  }

  Future<void> getGroupDetails(String bathId) async {
    groupDetails = await GroupService.getGroupDetails(bathId, govId.toString());
    MaterialPageRoute materialPageRoute = MaterialPageRoute(
        builder: (BuildContext context) =>
            GroupDetails(groupDetails: groupDetails));
    Navigator.push(context, materialPageRoute);
  }

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<CartProvider>(context);
    EasyLoading.init();
    return MaterialApp(
        color: Colors.white,
        localizationsDelegates: const [
          GlobalCupertinoLocalizations.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
        ],
        supportedLocales: const [Locale('ar', 'AE')],
        locale: const Locale('ar', 'AE'),
        home: Scaffold(
            appBar: AppBar(
              leading: IconButton(
                  icon: const Icon(Icons.arrow_back),
                  onPressed: () => Navigator.pop(context, false)),
              title: const Text(
                'المجموعـــات',
                style: TextStyle(
                  fontFamily: 'RPT Bold',
                  fontSize: 20.0,
                ),
              ),
              centerTitle: true,
              iconTheme: const IconThemeData(color: Colors.black),
              backgroundColor: Color.fromARGB(141, 190, 153, 82),
              shadowColor: Colors.black,
              elevation: 50.0,
              foregroundColor: Colors.black,
              // actions: [
              //   Badge(
              //     badgeContent: Consumer<CartProvider>(
              //       builder: (context, value, child) {
              //         return Text(
              //           value.getCounter().toString(),
              //           style: const TextStyle(
              //               color: Colors.white, fontWeight: FontWeight.bold),
              //         );
              //       },
              //     ),
              //     position: const BadgePosition(start: 30, bottom: 30),
              //     child: IconButton(
              //       onPressed: () {
              //         Navigator.push(
              //             context,
              //             MaterialPageRoute(
              //                 builder: (context) => const CartScreen()));
              //       },
              //       icon: const Icon(Icons.shopping_cart),
              //     ),
              //   ),
              //   const SizedBox(
              //     width: 20.0,
              //   ),
              // ],
            ),
            body: Container(
                decoration: const BoxDecoration(
                    border: Border(bottom: BorderSide(width: 50))),
                padding: const EdgeInsets.all(10.0),
                child: CustomScrollView(
                  slivers: <Widget>[
                    SliverList(
                      delegate: SliverChildBuilderDelegate(
                          (BuildContext context, int index) {
                        return Card(
                            color: index % 2 == 0
                                ? Color.fromARGB(255, 214, 210, 180)
                                : Color.fromARGB(255, 255, 255, 255),
                            child: ListTile(
                              title: Text(
                                  widget.groupList.elementAt(index).bathName +
                                      ' - ' +
                                      widget.groupList
                                          .elementAt(index)
                                          .bathColor,
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold)),
                              onTap: () {
                                EasyLoading.show(status: 'جارى البحث...');
                                getGroupDetails(widget.groupList
                                    .elementAt(index)
                                    .bathId
                                    .toString());
                              },
                            ));
                      }, childCount: widget.groupList.length),
                    )
                  ],
                ))));
  }
}
