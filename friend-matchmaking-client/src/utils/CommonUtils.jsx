export default function ConversionTabBarTitle(index) {
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
