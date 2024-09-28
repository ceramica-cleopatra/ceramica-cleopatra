import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class InputCustomizado extends StatelessWidget {
  const InputCustomizado(
      {key,
      required this.hint,
      this.obscure = false,
      this.icon = const Icon(Icons.person),
      required this.textController});

  final String hint;
  final bool obscure;
  final Icon icon;
  final TextEditingController textController;

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(left: 8, right: 8),
      child: TextField(
        obscureText: obscure,
        keyboardType: !obscure ? TextInputType.number : TextInputType.text,
        decoration: InputDecoration(
          icon: icon,
          border: InputBorder.none,
          hintText: hint,
          hintStyle: const TextStyle(
            color: Colors.grey,
            fontSize: 14,
          ),
        ),
        controller: textController,
      ),
    );
  }
}
