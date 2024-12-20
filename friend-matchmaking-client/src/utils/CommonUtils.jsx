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
