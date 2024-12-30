cmake_minimum_required(VERSION 3.27)
project(Demo Java)

find_package(Java COMPONENTS Development REQUIRED)
include(UseJava)

add_subdirectory(newpson/bullshit)
