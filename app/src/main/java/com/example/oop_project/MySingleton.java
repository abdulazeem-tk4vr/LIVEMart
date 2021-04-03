package com.example.oop_project;

public class MySingleton {


        private int myVariable = 0;

        // Getter/setter

        private static com.example.oop_project.MySingleton instance;

        public static com.example.oop_project.MySingleton getInstance() {
            if (instance == null)
                instance = new com.example.oop_project.MySingleton();
            return instance;
        }

        private MySingleton() { }

}
