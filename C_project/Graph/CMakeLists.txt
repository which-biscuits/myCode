cmake_minimum_required(VERSION 3.15)
project(Graph)

set(CMAKE_CXX_STANDARD 14)

add_executable(Graph Graph.cpp Queue.h)
add_executable(Class Queue.cpp Queue.h)