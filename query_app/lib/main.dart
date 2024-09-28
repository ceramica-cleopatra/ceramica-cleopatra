import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:provider/provider.dart';
import 'package:query_app/database/db_helper.dart';
import 'package:query_app/provider/cart_provider.dart';
import 'package:query_app/sehy_search_result_table.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'cart_screen.dart';
import 'crmk_search.dart';
import 'crmk_search_result_table.dart';
import 'dcre_search.dart';
import 'dcre_search_result_table.dart';
import 'flutter_barcode_scanner.dart';
import 'login_screen.dart';
import 'sehy_search.dart';

void main() {
  runApp(ChangeNotifierProvider(
    create: (_) => CartProvider(),
    child: const MaterialApp(
      home: Login(),
      debugShowCheckedModeBanner: false,
    ),
  ));
}

// ignore: unused_element
String _scanBarcode = '';

class Home extends StatefulWidget {
  const Home({
    Key? key,
  }) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> with SingleTickerProviderStateMixin {
  late AnimationController _drawerSlideController;

  @override
  void initState() {
    super.initState();
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
    _drawerSlideController = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 150),
    );
  }

  @override
  void dispose() {
    _drawerSlideController.dispose();
    super.dispose();
  }

  bool _isDrawerOpen() {
    return _drawerSlideController.value == 1.0;
  }

  bool _isDrawerOpening() {
    return _drawerSlideController.status == AnimationStatus.forward;
  }

  bool _isDrawerClosed() {
    return _drawerSlideController.value == 0.0;
  }

  void _toggleDrawer() {
    if (_isDrawerOpen() || _isDrawerOpening()) {
      _drawerSlideController.reverse();
    } else {
      _drawerSlideController.forward();
    }
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);

    final cart = Provider.of<CartProvider>(context);
    cart.getCounter();
    return MaterialApp(
        home: Scaffold(
      backgroundColor: Colors.white,
      appBar: _buildAppBar(),
      body: Stack(
        children: [
          _buildContent(),
          _buildDrawer(),
        ],
      ),
    ));
  }

  PreferredSizeWidget _buildAppBar() {
    EasyLoading.init();
    return AppBar(
      backgroundColor: Colors.transparent,
      elevation: 0.0,
      automaticallyImplyLeading: false,
      actions: [
        AnimatedBuilder(
          animation: _drawerSlideController,
          builder: (BuildContext context, Widget? child) {
            return IconButton(
              onPressed: _toggleDrawer,
              icon: _isDrawerOpen() || _isDrawerOpening()
                  ? const Icon(
                      Icons.clear,
                      color: Colors.black,
                    )
                  : const Icon(
                      Icons.menu,
                      color: Colors.black,
                    ),
            );
          },
        ),
        const SizedBox(
          width: 20.0,
        ),
        const SizedBox(
          width: 20.0,
        ),
      ],
    );
  }

  Widget _buildContent() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
    const SizedBox(
      height: 50,
    );
    return const Center(
        child: SizedBox(
      child: Image(image: AssetImage('assets/images/crmk.jpeg')),
    ));
  }

  Widget _buildDrawer() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
    return AnimatedBuilder(
      animation: _drawerSlideController,
      builder: (BuildContext context, Widget? child) {
        return FractionalTranslation(
          translation: Offset(1.0 - _drawerSlideController.value, 0.0),
          child: _isDrawerClosed() ? const SizedBox() : const Menu(),
        );
      },
    );
  }
}

class Menu extends StatefulWidget {
  const Menu({Key? key}) : super(key: key);

  @override
  _MenuState createState() => _MenuState();
}

class _MenuState extends State<Menu> with SingleTickerProviderStateMixin {
  static const List<String> _menuTitles = [
    'سيرامــــــــــيك',
    'ديكــــــــــــور',
    'صحــــــــــــــى'
  ];

  static const Duration _initialDelayTime = Duration(milliseconds: 50);
  static const Duration _itemSlideTime = Duration(milliseconds: 250);
  static const Duration _staggerTime = Duration(milliseconds: 50);
  static const Duration _buttonDelayTime = Duration(milliseconds: 150);
  static const Duration _buttonTime = Duration(milliseconds: 500);
  final Duration _animationDuration = _initialDelayTime +
      (_staggerTime * _menuTitles.length) +
      _buttonDelayTime +
      _buttonTime;

  late AnimationController _staggeredController;
  final List<Interval> _itemSlideIntervals = [];
  late Interval _buttonInterval;
  late String govId;

  @override
  void initState() {
    super.initState();
    validateLoginStatus();
    _createAnimationIntervals();

    _staggeredController = AnimationController(
      vsync: this,
      duration: _animationDuration,
    )..forward();
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
  }

  Future<void> validateLoginStatus() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    setState(() {
      govId = prefs.getInt('govId').toString();
    });
    String loginTime = prefs.getString('loginTime') ?? "2023-01-01 12:00:00";
    int hours = DateTime.now().difference(DateTime.parse(loginTime)).inHours;
    if (govId == "0" || hours >= 1) {
      MaterialPageRoute materialPageRoute =
          MaterialPageRoute(builder: (BuildContext context) => const Login());
      Navigator.push(context, materialPageRoute);
    }
  }

  void _createAnimationIntervals() {
    for (int i = 0; i < _menuTitles.length; ++i) {
      final Duration startTime = _initialDelayTime + (_staggerTime * i);
      final Duration endTime = startTime + _itemSlideTime;
      _itemSlideIntervals.add(
        Interval(
          startTime.inMilliseconds / _animationDuration.inMilliseconds,
          endTime.inMilliseconds / _animationDuration.inMilliseconds,
        ),
      );
    }

    final Duration buttonStartTime =
        Duration(milliseconds: _menuTitles.length * 50) + _buttonDelayTime;
    final Duration buttonEndTime = buttonStartTime + _buttonTime;
    _buttonInterval = Interval(
      buttonStartTime.inMilliseconds / _animationDuration.inMilliseconds,
      buttonEndTime.inMilliseconds / _animationDuration.inMilliseconds,
    );
  }

  @override
  void dispose() {
    _staggeredController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Builder(builder: (context) {
      SystemChrome.setPreferredOrientations([
        DeviceOrientation.landscapeRight,
        DeviceOrientation.landscapeLeft,
      ]);
      return Container(
        color: Colors.white,
        child: Stack(
          fit: StackFit.expand,
          children: [
            _buildFlutterLogo(),
            _buildContent(),
          ],
        ),
      );
    });
  }

  Widget _buildFlutterLogo() {
    return const Center(
      child: Opacity(
        opacity: 0.15,
        child:
            Center(child: Image(image: AssetImage('assets/images/crmk.jpeg'))),
      ),
    );
  }

  Widget _buildContent() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.landscapeRight,
      DeviceOrientation.landscapeLeft,
    ]);
    return Column(
      crossAxisAlignment: CrossAxisAlignment.end,
      children: [
        const SizedBox(height: 16),
        ..._buildListItems(),
        const Spacer(),
        _buildGetStartedButton(),
      ],
    );
  }

  List<Widget> _buildListItems() {
    final List<Widget> listItems = <Widget>[];
    for (int i = 0; i < _menuTitles.length; ++i) {
      listItems.add(
        AnimatedBuilder(
          animation: _staggeredController,
          builder: (BuildContext context, Widget? child) {
            final double animationPercent = Curves.easeOut.transform(
              _itemSlideIntervals[i].transform(_staggeredController.value),
            );
            final double opacity = animationPercent;
            final double slideDistance = (1.0 - animationPercent) * -400;

            return Opacity(
              opacity: opacity,
              child: Transform.translate(
                offset: Offset(slideDistance, 0),
                child: child,
              ),
            );
          },
          child: Center(
            child: Padding(
              padding: const EdgeInsets.all(12.0),
              child: SizedBox(
                height: 50,
                width: 180,
                child: Container(
                  decoration: BoxDecoration(
                    //DecorationImage
                    border: Border.all(
                        color: Color.fromARGB(0, 105, 100, 20),
                        width: 4.0,
                        style: BorderStyle.solid), //Border.all
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(10.0),
                      topRight: Radius.circular(10.0),
                      bottomLeft: Radius.circular(10.0),
                      bottomRight: Radius.circular(10.0),
                    ),
                    gradient: LinearGradient(
                      colors: [
                        Color.fromARGB(255, 245, 229, 177),
                        Color.fromARGB(0, 43, 37, 2)
                      ],
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: Color.fromARGB(141, 172, 127, 44),
                        offset: const Offset(
                          5.0,
                          5.0,
                        ),
                        blurRadius: 10.0,
                        spreadRadius: 2.0,
                      ), //BoxShadow
                      BoxShadow(
                        color: Colors.white,
                        offset: const Offset(0.0, 0.0),
                        blurRadius: 0.0,
                        spreadRadius: 0.0,
                      ), //BoxShadow
                    ],
                  ),
                  child: Center(
                    child: RichText(
                      text: TextSpan(
                          text: _menuTitles[i],
                          style: const TextStyle(
                            color: Colors.black,
                            fontSize: 25,
                            fontWeight: FontWeight.bold,
                          ),
                          recognizer: TapGestureRecognizer()
                            ..onTap = () {
                              _route(context, i + 1);
                            }),
                    ),
                  ),
                  //BoxDecoration
                ), //Container
              ), //SizedBox
            ), //Padding
          ),
        ),
      );
    }
    return listItems;
  }

  Widget _buildGetStartedButton() {
    return SizedBox(
      width: double.infinity,
      child: Padding(
        padding: const EdgeInsets.all(20.0),
        child: AnimatedBuilder(
          animation: _staggeredController,
          builder: (BuildContext context, Widget? child) {
            final double animationPercent = Curves.elasticOut.transform(
                _buttonInterval.transform(_staggeredController.value));
            final double opacity = animationPercent.clamp(0.0, 1.0);
            final double scale = (animationPercent * 0.5) + 0.5;

            return Opacity(
              opacity: opacity,
              child: Transform.scale(
                scale: scale,
                child: child,
              ),
            );
          },
          child: ElevatedButton(
            style: ElevatedButton.styleFrom(
              shape: const StadiumBorder(),
              primary: Color.fromARGB(255, 165, 154, 119),
              shadowColor: Color.fromARGB(0, 110, 109, 11),
              padding: const EdgeInsets.symmetric(horizontal: 48, vertical: 6),
            ),
            onPressed: () {
              _route(context, 4);
            },
            child: const Text(
              'QR بحـــث',
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 25,
                  fontWeight: FontWeight.bold),
            ),
          ),
        ),
      ),
    );
  }

  _route(BuildContext context, int pageId) async {
    MaterialPageRoute materialPageRoute;
    if (pageId == 1) {
      materialPageRoute = MaterialPageRoute(
          builder: (BuildContext context) => const CrmkSearch());
      await Navigator.push(context, materialPageRoute);
    } else if (pageId == 2) {
      materialPageRoute = MaterialPageRoute(
          builder: (BuildContext context) => const DcreSearch());
      await Navigator.push(context, materialPageRoute);
    } else if (pageId == 3) {
      materialPageRoute = MaterialPageRoute(
          builder: (BuildContext context) => const SehySearch());
      await Navigator.push(context, materialPageRoute);
    } else if (pageId == 4) {
      scanBarcodeNormal();
    }
  }

  Future<void> scanBarcodeNormal() async {
    String barcodeScanRes;
    try {
      barcodeScanRes = await FlutterBarcodeScanner.scanBarcode(
          '#ff6666', 'Cancel', true, ScanMode.BARCODE);
      if (barcodeScanRes.isNotEmpty) {
        if (barcodeScanRes.contains("qr=")) {
          barcodeScanRes =
              barcodeScanRes.substring(barcodeScanRes.indexOf("qr=") + 3);
        }
        barcodeScanRes =
            barcodeScanRes.substring(barcodeScanRes.lastIndexOf('/') + 1);
        print(barcodeScanRes);
        List<String> params = barcodeScanRes.split('-');
        if (params[0] == 'C') {
          final MaterialPageRoute materialPageRoute = MaterialPageRoute(
              builder: (BuildContext context) => CrmkSearchResultTable(
                  typeId: params[1],
                  sizeId: params[2],
                  factoryNo: params[3],
                  dekalaId: params[4],
                  frz: '1',
                  govId: govId));
          await Navigator.push(context, materialPageRoute);
        } else if (params[0] == 'D') {
          final MaterialPageRoute materialPageRoute = MaterialPageRoute(
              builder: (BuildContext context) => DcreSearchResultTable(
                  typeId: params[1],
                  sizeId: params[2],
                  factoryNo: params[3],
                  dekalaId: params[4],
                  frz: '1',
                  colorId: params[5],
                  tablow: params[6],
                  govId: govId));
          await Navigator.push(context, materialPageRoute);
        } else if (params[0] == 'S') {
          final MaterialPageRoute materialPageRoute = MaterialPageRoute(
              builder: (BuildContext context) => SehySearchResultTable(
                  takmName: '',
                  typeId: params[1],
                  nameId: params[2],
                  dekalaId: params[3],
                  colorId: params[4],
                  frz: govId));
          await Navigator.push(context, materialPageRoute);
        }
      }
    } on PlatformException {
      barcodeScanRes = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _scanBarcode = barcodeScanRes;
    });
  }
}
