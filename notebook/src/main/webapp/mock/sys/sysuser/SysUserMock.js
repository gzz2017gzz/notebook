/*用户模拟数据,作者:高振中,日期:2018-04-03 11:40:55*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/sysUser/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        name: "@word(5,10)",//用户
        password: "@word(5,10)",//密码
        login_id: "@word(5,10)",//角色ID
      }],
      "number": '@integer(100,200)',
      "size": 10,
      "totalElements": 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },
  'POST /api/sysUser/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/sysUser/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/sysUser/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        name: "@word(5,10)",//用户
        password: "@word(5,10)",//密码
        login_id: "@word(5,10)",//角色ID
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'POST /api/sysUser/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}