import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_svg/svg.dart';
import 'package:query_app/main.dart';
import 'package:query_app/service/login_service.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../database/db_helper.dart';

class BotaoAnimado extends StatelessWidget {
  AnimationController controller;
  Animation<double> largura;
  Animation<double> altura;
  Animation<double> radius;
  Animation<double> opacidade;
  TextEditingController usercodeController;
  TextEditingController passwordController;

  BotaoAnimado(
      {Key? key,
      required this.controller,
      required this.usercodeController,
      required this.passwordController})
      : largura = Tween<double>(
          begin: 0,
          end: 500,
        ).animate(
          CurvedAnimation(
            parent: controller,
            curve: const Interval(0.0, 0.5),
          ),
        ),
        altura = Tween<double>(
          begin: 0,
          end: 50,
        ).animate(
          CurvedAnimation(
            parent: controller,
            curve: const Interval(0.5, 0.7),
          ),
        ),
        radius = Tween<double>(
          begin: 0,
          end: 20,
        ).animate(
          CurvedAnimation(
            parent: controller,
            curve: const Interval(0.6, 1.0),
          ),
        ),
        opacidade = Tween<double>(
          begin: 0,
          end: 1,
        ).animate(
          CurvedAnimation(
            parent: controller,
            curve: const Interval(0.6, 0.8),
          ),
        ),
        super(key: key);

  Widget _buildAnimation(BuildContext context, Widget? widget) {
    void _setPrefsItems(int govId, DateTime loginTime) async {
      SharedPreferences prefs = await SharedPreferences.getInstance();
      prefs.setInt('govId', govId);
      prefs.setString('loginTime', loginTime.toString());
    }

    return InkWell(
      onTap: () {},
      child: Container(
        width: largura.value,
        height: altura.value,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(radius.value),
          gradient: const LinearGradient(
            colors: [
              Color.fromARGB(255, 165, 154, 119),
              Color.fromARGB(255, 165, 154, 119),
            ],
          ),
        ),
        child: Center(
          child: FadeTransition(
            opacity: opacidade,
            child: ElevatedButton(
              style: ElevatedButton.styleFrom(
                shape: const StadiumBorder(),
                primary: Color.fromARGB(255, 165, 154, 119),
                shadowColor: Color.fromARGB(0, 110, 109, 11),
                fixedSize: Size(500, 50),
                padding:
                    const EdgeInsets.symmetric(horizontal: 48, vertical: 6),
              ),
              onPressed: () async {
                EasyLoading.show(status: 'يرجى الإنتظار ...');
                if (usercodeController.text.isEmpty) {
                  ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                      behavior: SnackBarBehavior.floating,
                      backgroundColor: Colors.transparent,
                      elevation: 0,
                      content: CustomSnackBarContent(
                        errorText: 'يجب إدخال كود الموظف',
                      )));
                  EasyLoading.dismiss();
                } else if (passwordController.text.isEmpty) {
                  ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                      behavior: SnackBarBehavior.floating,
                      backgroundColor: Colors.transparent,
                      elevation: 0,
                      content: CustomSnackBarContent(
                          errorText: 'يجب إدخال كلمة المرور')));
                  EasyLoading.dismiss();
                } else {
                  int validationResult = await LoginService.validateUser(
                      usercodeController.text, passwordController.text);
                  if (validationResult == -1) {
                    ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                        behavior: SnackBarBehavior.floating,
                        backgroundColor: Colors.transparent,
                        elevation: 0,
                        content: CustomSnackBarContent(
                            errorText: 'كود الموظف او كلمة المرور غير صحيح')));
                    EasyLoading.dismiss();
                  } else if (validationResult == -2) {
                    ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
                        behavior: SnackBarBehavior.floating,
                        backgroundColor: Colors.transparent,
                        elevation: 0,
                        content: CustomSnackBarContent(
                            errorText:
                                'غير مسموح بالدخول فى غير فترات العمل')));
                    EasyLoading.dismiss();
                  } else {
                    DBHelper dbHelper = DBHelper();
                    dbHelper.initDatabase();
                    _setPrefsItems(validationResult, DateTime.now());
                    EasyLoading.dismiss();
                    MaterialPageRoute materialPageRoute = MaterialPageRoute(
                        builder: (BuildContext context) => const Home());
                    Navigator.pushReplacement(context, materialPageRoute);
                  }
                }
              },
              child: const Text(
                'دخول',
                style: TextStyle(
                    color: Colors.black,
                    fontSize: 20,
                    fontWeight: FontWeight.bold),
              ),
            ),
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: controller,
      builder: _buildAnimation,
    );
  }
}

class CustomSnackBarContent extends StatelessWidget {
  final String errorText;
  const CustomSnackBarContent({Key? key, required this.errorText});
  @override
  Widget build(BuildContext context) {
    return Stack(
      clipBehavior: Clip.none,
      children: [
        Container(
          padding: EdgeInsets.all(16),
          height: 90,
          decoration: const BoxDecoration(
              color: Color.fromARGB(255, 163, 53, 46),
              borderRadius: BorderRadius.all(Radius.circular(20))),
          child: Row(
            children: [
              const SizedBox(
                width: 50,
              ),
              Expanded(
                  child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    "خطأ",
                    style: TextStyle(
                        fontSize: 20,
                        color: Colors.white,
                        fontWeight: FontWeight.bold),
                  ),
                  const Spacer(),
                  Text(
                    errorText,
                    style: const TextStyle(color: Colors.white, fontSize: 16),
                    maxLines: 2,
                    overflow: TextOverflow.ellipsis,
                  )
                ],
              ))
            ],
          ),
        ),
        Positioned(
            top: -20,
            left: 0,
            child: Stack(
              alignment: Alignment.center,
              children: [
                Positioned(
                  top: 10,
                  child: SvgPicture.asset("asset/images/error.svg", height: 16),
                )
              ],
            )),
        Positioned(
          bottom: 0,
          child: ClipRRect(
            borderRadius: BorderRadius.only(bottomLeft: Radius.circular(20)),
            child: Stack(
              children: [
                SvgPicture.asset(
                  "asset/images/bubble.svg",
                  height: 48,
                  width: 40,
                  color: Color.fromARGB(255, 163, 53, 46),
                )
              ],
            ),
          ),
        )
      ],
    );
  }
}
