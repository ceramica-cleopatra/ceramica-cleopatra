import 'package:data_table_2/data_table_2.dart';
import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:query_app/model/group_details_result.dart';
import 'package:query_app/service/group_service.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

import 'crmk_search_result_table.dart';
import 'dcre_search_result_table.dart';
import 'login_screen.dart';

class GroupDetails extends StatefulWidget {
  GroupDetails({Key? key, required this.groupDetails}) : super(key: key);
  List<GroupDetailsResult> groupDetails;
  _GroupDetailsState createState() => _GroupDetailsState();
}

class _GroupDetailsState extends State<GroupDetails> {
  List<DataRow> rows = [];
  List<DataColumn> columns = [];
  _GroupDetailsState();
  String govId = "0";
  void initiateColumns() {
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "النوع",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "المقاس",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "الرقم",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "الديكالة",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "اللون",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "التابلوه",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "فرز 1",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
    columns.add(DataColumn(
        label: Container(
            padding: const EdgeInsets.all(8.0),
            alignment: Alignment.center,
            child:
                Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Text(
                "فرز 2",
                textAlign: TextAlign.center,
                style: const TextStyle(
                    fontStyle: FontStyle.italic,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
              )
            ]))));
  }

  void initiateRows() {
    int i = 0;
    for (GroupDetailsResult groupDetailsResult in widget.groupDetails) {
      i++;
      List<DataCell> cells = [];
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.typeName,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.sizeName,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.factoryNo,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.dekalaName,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.colorName,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.tablow,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.free1,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      cells.add(DataCell(InkWell(
          onTap: () {
            _route(
                groupDetailsResult.crmkDcre,
                groupDetailsResult.typeId,
                groupDetailsResult.sizeId,
                groupDetailsResult.factoryNo,
                groupDetailsResult.dekalaId,
                groupDetailsResult.tablow,
                groupDetailsResult.colorId);
          },
          child: SizedBox(
            width: double.infinity,
            child: Container(
              height: double.infinity,
              width: double.infinity,
              color: i > 0 && i.isOdd
                  ? Colors.white
                  : Color.fromARGB(255, 248, 246, 230),
              child: Center(
                  child: Text(
                groupDetailsResult.free2,
                textAlign: TextAlign.center,
              )),
            ),
          ))));
      rows.add(DataRow(cells: cells));
    }
  }

  @override
  void initState() {
    validateLoginStatus();
    EasyLoading.dismiss();
    initiateColumns();
    initiateRows();
  }

  @override
  void dispose() {
    super.dispose();
  }

  void _route(String crmkDcre, String typeId, String sizeId, String factoryNo,
      String dekalaId, String tablow, String colorId) {
    if (crmkDcre == 'C') {
      Navigator.push(
          context,
          MaterialPageRoute(
            builder: (BuildContext context) => CrmkSearchResultTable(
                typeId: typeId,
                sizeId: sizeId,
                factoryNo: factoryNo,
                dekalaId: dekalaId,
                frz: '1',
                govId: govId.toString()),
          ));
    } else if (crmkDcre == 'D') {
      Navigator.push(
          context,
          MaterialPageRoute(
              builder: (BuildContext context) => DcreSearchResultTable(
                  typeId: typeId,
                  sizeId: sizeId,
                  factoryNo: factoryNo,
                  dekalaId: dekalaId,
                  frz: '1',
                  colorId: colorId,
                  tablow: tablow,
                  govId: govId.toString())));
    }
  }

  Future<void> validateLoginStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    govId = prefs.getInt('govId').toString();
    String loginTime = prefs.getString('loginTime') ?? "2023-01-01 12:00:00";
    int hours = DateTime.now().difference(DateTime.parse(loginTime)).inHours;
    if (govId == "0" || hours >= 1) {
      MaterialPageRoute materialPageRoute =
          MaterialPageRoute(builder: (BuildContext context) => const Login());
      Navigator.push(context, materialPageRoute);
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        localizationsDelegates: const [
          GlobalCupertinoLocalizations.delegate,
          GlobalMaterialLocalizations.delegate,
          GlobalWidgetsLocalizations.delegate,
        ],
        supportedLocales: const [
          Locale('ar', 'AE')
        ],
        locale: const Locale('ar', 'AE'),
        home: Scaffold(
            appBar: AppBar(
              leading: IconButton(
                  icon: const Icon(Icons.arrow_back),
                  onPressed: () => Navigator.pop(context, false)),
              title: const Text(
                'تفاصيل المجموعة',
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
            body: SizedBox(
              width: double.infinity,
              child: DataTable2(
                headingRowHeight: 60,
                columnSpacing: 0,
                dataRowHeight: 45,
                horizontalMargin: 1,
                dividerThickness: 1,
                showBottomBorder: true,
                headingTextStyle: const TextStyle(
                  fontWeight: FontWeight.w900,
                  color: Colors.black,
                ),
                headingRowColor: MaterialStateColor.resolveWith(
                    (Set<MaterialState> states) =>
                        Color.fromARGB(255, 165, 154, 119)),
                columns: this.columns,
                rows: this.rows,
              ),
            )));
  }
}
