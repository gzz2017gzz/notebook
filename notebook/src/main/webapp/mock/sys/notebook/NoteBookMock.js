/*记事本模拟数据,作者:高振中,日期:2018-04-03 11:40:55*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/noteBook/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        title: "@word(5,10)",//标题
        content: "@word(5,10)",//内容
        release_date: "@integer(100,200)",//发布日期
        author: "@word(5,10)",//作者
        status: "@word(5,10)",//状态
        level: "@word(5,10)",//优先级
        type: "@word(5,10)",//问题类型
      }],
      "number": '@integer(100,200)',
      "size": 10,
      "totalElements": 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },
  'POST /api/noteBook/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/noteBook/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/noteBook/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        id: "@integer(100,200)",//主键
        title: "@word(5,10)",//标题
        content: "@word(5,10)",//内容
        release_date: "@integer(100,200)",//发布日期
        author: "@word(5,10)",//作者
        status: "@word(5,10)",//状态
        level: "@word(5,10)",//优先级
        type: "@word(5,10)",//问题类型
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'POST /api/noteBook/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}