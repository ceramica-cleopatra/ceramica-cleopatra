import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:infinite_scroll_pagination/infinite_scroll_pagination.dart';
import 'package:query_app/service/dcre_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'dcre_search_result_table.dart';
import 'login_screen.dart';
import 'model/dcre_search_result.dart';

class DcreList extends StatefulWidget {
  DcreList(
      {Key? key,
      required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.dekalaId,
      required this.dekalaName,
      required this.factoryNo,
      required this.colorId,
      required this.colorName,
      required this.tablow,
      required this.frz})
      : super(key: key);
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String dekalaId;
  String dekalaName;
  String factoryNo;
  String colorId;
  String colorName;
  String tablow;
  String frz;
  @override
  DcreListState createState() => DcreListState(
      typeId: typeId,
      typeName: typeName,
      sizeId: sizeId,
      sizeName: sizeName,
      dekalaId: dekalaId,
      dekalaName: dekalaName,
      factoreyNo: factoryNo,
      colorId: colorId,
      colorName: colorName,
      tablow: tablow,
      frz: frz);
}

class DcreListState extends State<DcreList> {
  DcreListState(
      {required this.typeId,
      required this.typeName,
      required this.sizeId,
      required this.sizeName,
      required this.dekalaId,
      required this.dekalaName,
      required this.factoreyNo,
      required this.colorId,
      required this.colorName,
      required this.tablow,
      required this.frz});
  String typeId;
  String typeName;
  String sizeId;
  String sizeName;
  String dekalaId;
  String dekalaName;
  String factoreyNo;
  String colorId;
  String colorName;
  String tablow;
  String frz;
  static const int _pageSize = 17;

  final PagingController<int, DcreSearchResult> _pagingController =
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
      final List<DcreSearchResult> newItems =
          await DcreService.getDcreSearchResult(
              typeId,
              typeName,
              sizeId,
              sizeName,
              dekalaId,
              dekalaName,
              factoreyNo,
              frz,
              colorId,
              colorName,
              tablow,
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
                'ديكـــــور',
                style: TextStyle(fontFamily: 'RPT Bold', fontSize: 20.0),
              ),
              centerTitle: true,
              iconTheme: const IconThemeData(color: Colors.black),
              backgroundColor: Color.fromARGB(141, 190, 153, 82),
              shadowColor: Colors.black,
              elevation: 50.0,
              foregroundColor: Colors.black,
            ),
            body: Container(
                decoration: const BoxDecoration(
                    border: Border(bottom: BorderSide(width: 50))),
                padding: const EdgeInsets.all(10.0),
                child: CustomScrollView(
                  slivers: <Widget>[
                    PagedSliverList<int, DcreSearchResult>(
                      pagingController: _pagingController,
                      builderDelegate:
                          PagedChildBuilderDelegate<DcreSearchResult>(
                        itemBuilder: (BuildContext context,
                                DcreSearchResult item, int index) =>
                            Card(
                                color: index % 2 == 0
                                    ? Color.fromARGB(255, 214, 210, 180)
                                    : Color.fromARGB(255, 255, 255, 255),
                                child: ListTile(
                                  title: Text(
                                      '${item.typeName} / ${item.sizeName} / ${item.factoryNo} / ${item.dekalaName} / ${item.colorName} / ${item.tablow} / ${item.frz}',
                                      style: const TextStyle(
                                          color: Colors.black,
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold)),
                                  onTap: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                DcreSearchResultTable(
                                                    typeId: item.typeId,
                                                    sizeId: item.sizeId,
                                                    factoryNo: item.factoryNo,
                                                    dekalaId: item.dekalaId,
                                                    frz: item.frz,
                                                    colorId: widget.colorId,
                                                    tablow: widget.tablow,
                                                    govId: govId.toString())));
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
