export function ConversionTabBarTitle(index) {
    switch (index) {
        case 'index':
            return '主页';
        case 'team':
            return "队伍";
        case 'user':
            return "我的";
        default:
            console.error("转换TabBar标题错误")
            return ""
    }
}



/**
 * 深拷贝
 * @param source
 */
export function cloneDeep(source){
    let target = {};
    for(let key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
            if (typeof source[key] === 'object') {
                target[key] = cloneDeep(source[key]); // 注意这里
            } else {
                target[key] = source[key];
            }
        }
    }
    return target;
}

/**
 * 格式化标准时间
 * @param time "2024-12-24T05:06:42.000+00:00"
 */
export const formatStandTime = (time: string) => {
    let date = new Date(time);
    // 使用 `Intl.DateTimeFormat` 来格式化日期
    let formatter = new Intl.DateTimeFormat('zh-cn', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false,  // 使用24小时制
    });

    let formattedTime = formatter.format(date);
    console.log(formattedTime);
    return formattedTime;
}
