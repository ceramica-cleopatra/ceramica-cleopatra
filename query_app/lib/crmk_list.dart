import 'package:badges/badges.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:provider/provider.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'cart_screen.dart';
import 'crmk_search_result_table.dart';
import 'database/db_helper.dart';
import 'login_screen.dart';
import 'model/crmk_search_result.dart';
import 'service/crmk_service.dart';

class CrmkList extends StatefulWidget {
  CrmkList(
      {Key? key,
      required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.dekalaId,
      required this.dekalaName,
      required this.factoryNo,
      required this.frz})
      : super(key: key);
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String dekalaId;
  String dekalaName;
  String factoryNo;
  String frz;
  @override
  CrmkListState createState() => CrmkListState(
      typeId: typeId,
      typeName: typeName,
      sizeId: sizeId,
      sizeName: sizeName,
      dekalaId: dekalaId,
      dekalaName: dekalaName,
      factoreyNo: factoryNo,
      frz: frz);
}

class CrmkListState extends State<CrmkList> {
  CrmkListState(
      {required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.dekalaId,
      required this.dekalaName,
      required this.factoreyNo,
      required this.frz});
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String dekalaId;
  String dekalaName;
  String factoreyNo;
  String frz;
  static const int _pageSize = 17;
  DBHelper dbHelper = DBHelper();

  final PagingController<int, CrmkSearchResult> _pagingController =
      PagingController(firstPageKey: 0);

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

  @override
  void initState() {
    validateLoginStatus();
    _pagingController.addPageRequestListener((int pageKey) {
      _fetchPage(pageKey);
    });

    super.initState();
  }

  Future<void> _fetchPage(pageKey) async {
    try {
      final List<CrmkSearchResult> newItems =
          await CrmkService.getCrmkSearchResult(
              typeId,
              typeName,
              sizeId,
              sizeName,
              dekalaId,
              dekalaName,
              factoreyNo,
              frz,
              pageKey.toString(),
              _pageSize.toString());

      final bool isLastPage = newItems.length < _pageSize;
      if (isLastPage) {
        _pagingController.appendLastPage(newItems);
      } else {
        final nextPageKey = pageKey + newItems.length;
        _pagingController.appendPage(newItems, nextPageKey);
      }
    } catch (error) {
      _pagingController.error = error;
    }
  }

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<CartProvider>(context);
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
                'سيراميـــك',
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
                    PagedSliverList<int, CrmkSearchResult>(
                      pagingController: _pagingController,
                      builderDelegate:
                          PagedChildBuilderDelegate<CrmkSearchResult>(
                        itemBuilder: (BuildContext context,
                                CrmkSearchResult item, int index) =>
                            Card(
                                color: index % 2 == 0
                                    ? Color.fromARGB(255, 214, 210, 180)
                                    : Color.fromARGB(255, 255, 255, 255),
                                child: ListTile(
                                  title: Text(
                                      '${item.typeName} / ${item.sizeName} / ${item.factoryNo} / ${item.dekalaName} / ${item.frz}',
                                      style: const TextStyle(
                                          color: Colors.black,
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold)),
                                  onTap: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                          builder: (BuildContext context) =>
                                              CrmkSearchResultTable(
                                                  typeId: item.typeId,
                                                  sizeId: item.sizeId,
                                                  factoryNo: item.factoryNo,
                                                  dekalaId: item.dekalaId,
                                                  frz: item.frz,
                                                  govId: govId.toString()),
                                        ));
                                  },
                                )),
                      ),
                    )
                  ],
                ))));
  }

  @override
  void dispose() {
    _pagingController.dispose();
    super.dispose();
  }
}
