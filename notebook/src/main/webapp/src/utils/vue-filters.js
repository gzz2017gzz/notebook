import moment from 'moment';

function DateFormat(time) {
  if (time != null) return new moment(time).format('YYYY-MM-DD HH:mm:ss')
}

export default function (Vue) {
  Vue.filter('DateFormat', DateFormat);
}
