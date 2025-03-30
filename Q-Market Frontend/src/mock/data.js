// 模拟商品数据
export const products = [
  {
    id: 1,
    name: '高端智能手机 Pro Max',
    description: '最新一代处理器，超长续航，专业级摄像头',
    price: 5999,
    originalPrice: 6999,
    image: 'https://picsum.photos/id/160/300/300',
    images: [
      'https://picsum.photos/id/160/800/800',
      'https://picsum.photos/id/167/800/800',
      'https://picsum.photos/id/169/800/800',
      'https://picsum.photos/id/162/800/800'
    ],
    rating: 4.9,
    reviewCount: 1024,
    category: '手机数码',
    tags: ['畅销', '新品', '限时优惠'],
    brand: 'TechBrand',
    specs: '经典黑 / 256GB',
    stock: 198,
    isNew: true,
    attributes: {
      '屏幕尺寸': '6.7英寸',
      '处理器': 'A16仿生芯片',
      '后置摄像头': '三摄（4800万+1200万+1200万）',
      '前置摄像头': '1200万像素',
      '电池容量': '4500mAh',
      '防水等级': 'IP68',
      '操作系统': 'iOS 16'
    },
    sku: [
      { id: 101, color: '经典黑', storage: '128GB', price: 5999, stock: 50 },
      { id: 102, color: '经典黑', storage: '256GB', price: 6499, stock: 30 },
      { id: 103, color: '雪峰白', storage: '128GB', price: 5999, stock: 40 },
      { id: 104, color: '雪峰白', storage: '256GB', price: 6499, stock: 35 },
      { id: 105, color: '深海蓝', storage: '128GB', price: 5999, stock: 25 },
      { id: 106, color: '深海蓝', storage: '256GB', price: 6499, stock: 18 }
    ]
  },
  {
    id: 2,
    name: '轻薄笔记本电脑 Pro',
    description: '超轻薄设计，强劲性能，全天续航',
    price: 7999,
    originalPrice: 8999,
    image: 'https://picsum.photos/id/119/300/300',
    images: [
      'https://picsum.photos/id/119/800/800',
      'https://picsum.photos/id/180/800/800',
      'https://picsum.photos/id/201/800/800'
    ],
    rating: 4.8,
    reviewCount: 512,
    category: '电脑办公',
    tags: ['畅销', '新品'],
    brand: 'TechBrand',
    specs: '银色 / 16GB / 512GB',
    stock: 73,
    isNew: true,
    attributes: {
      '屏幕尺寸': '14英寸',
      '处理器': 'Intel Core i7-1260P',
      '内存': '16GB',
      '存储': '512GB SSD',
      '显卡': 'Intel Iris Xe',
      '电池续航': '最长18小时',
      '操作系统': 'Windows 11'
    }
  },
  {
    id: 3,
    name: '智能手表 Series 7',
    description: '实时健康监测，全天候活动追踪',
    price: 2499,
    originalPrice: 2999,
    image: 'https://picsum.photos/id/111/300/300',
    images: [
      'https://picsum.photos/id/111/800/800',
      'https://picsum.photos/id/112/800/800',
      'https://picsum.photos/id/113/800/800'
    ],
    rating: 4.7,
    reviewCount: 768,
    category: '智能穿戴',
    tags: ['热卖', '限时优惠'],
    brand: 'TechBrand',
    specs: '典雅白 / GPS版',
    stock: 120,
    isNew: false,
    attributes: {
      '屏幕尺寸': '1.8英寸',
      '防水等级': '5ATM',
      '续航时间': '最长18小时',
      '健康功能': '心率监测、血氧监测、睡眠监测',
      '运动模式': '超过90种',
      '蓝牙': '5.0'
    }
  },
  {
    id: 4,
    name: '无线降噪耳机 Pro',
    description: '主动降噪，高清音质，长久续航',
    price: 1599,
    originalPrice: 1899,
    image: 'https://picsum.photos/id/112/300/300',
    images: [
      'https://picsum.photos/id/112/800/800',
      'https://picsum.photos/id/114/800/800',
      'https://picsum.photos/id/115/800/800'
    ],
    rating: 4.6,
    reviewCount: 384,
    category: '手机数码',
    tags: ['热卖'],
    brand: 'SoundMaster',
    specs: '星空黑',
    stock: 95,
    isNew: false,
    attributes: {
      '降噪级别': '最高可达40dB',
      '续航时间': '单次30小时',
      '蓝牙版本': '5.2',
      '防水等级': 'IPX4',
      '重量': '255g',
      '麦克风': '4个麦克风'
    }
  },
  {
    id: 5,
    name: '专业相机 DSLR X100',
    description: '专业摄影利器，卓越画质',
    price: 8999,
    originalPrice: 9999,
    image: 'https://picsum.photos/id/120/300/300',
    images: [
      'https://picsum.photos/id/120/800/800',
      'https://picsum.photos/id/121/800/800',
      'https://picsum.photos/id/122/800/800'
    ],
    rating: 4.9,
    reviewCount: 256,
    category: '摄影摄像',
    tags: ['专业', '新品'],
    brand: 'OptiMax',
    specs: '标准套装',
    stock: 32,
    isNew: true,
    attributes: {
      '传感器': 'APS-C CMOS',
      '有效像素': '2610万',
      '连拍速度': '最高14张/秒',
      '视频拍摄': '4K 60P',
      '屏幕': '3.0英寸可翻转触摸屏',
      '防抖': '5轴机身防抖'
    }
  },
  {
    id: 6,
    name: '智能扫地机器人 X9',
    description: '激光导航，智能避障，自动回充',
    price: 2999,
    originalPrice: 3599,
    image: 'https://picsum.photos/id/118/300/300',
    images: [
      'https://picsum.photos/id/118/800/800',
      'https://picsum.photos/id/119/800/800',
      'https://picsum.photos/id/117/800/800'
    ],
    rating: 4.5,
    reviewCount: 320,
    category: '家用电器',
    tags: ['畅销', '智能'],
    brand: 'SmartHome',
    specs: '高级版',
    stock: 68,
    isNew: false,
    attributes: {
      '清扫方式': 'LDS激光导航',
      '续航时间': '200分钟',
      '吸力': '4000Pa',
      '尘盒容量': '500ml',
      '水箱容量': '300ml',
      '噪音': '≤65dB'
    }
  },
  {
    id: 7,
    name: '高端厨师机',
    description: '多功能搅拌，精准控温',
    price: 3999,
    originalPrice: 4599,
    image: 'https://picsum.photos/id/116/300/300',
    images: [
      'https://picsum.photos/id/116/800/800',
      'https://picsum.photos/id/170/800/800',
      'https://picsum.photos/id/171/800/800'
    ],
    rating: 4.7,
    reviewCount: 189,
    category: '厨房电器',
    tags: ['高端', '多功能'],
    brand: 'ChefMaster',
    specs: '典雅白',
    stock: 42,
    isNew: false,
    attributes: {
      '功率': '1200W',
      '容量': '5L',
      '转速': '1200转/分钟',
      '功能': '搅拌、揉面、打蛋、绞肉',
      '预设程序': '20种',
      '材质': '不锈钢+ABS'
    }
  },
  {
    id: 8,
    name: '手机 Ultra轻薄版',
    description: '轻薄机身，强劲性能，长效续航',
    price: 4299,
    originalPrice: 4899,
    image: 'https://picsum.photos/id/168/300/300',
    images: [
      'https://picsum.photos/id/168/800/800',
      'https://picsum.photos/id/167/800/800',
      'https://picsum.photos/id/166/800/800'
    ],
    rating: 4.4,
    reviewCount: 278,
    category: '手机数码',
    tags: ['轻薄', '性价比'],
    brand: 'MobiTech',
    specs: '极光蓝 / 128GB',
    stock: 85,
    isNew: true,
    attributes: {
      '屏幕': '6.4英寸AMOLED',
      '处理器': 'Snapdragon 8 Gen 1',
      '电池': '4500mAh',
      '摄像头': '主摄5000万+超广角1200万+长焦800万',
      '厚度': '7.1mm',
      '重量': '175g'
    }
  },
  {
    id: 9,
    name: '智能音箱 Voice Pro',
    description: '360°音效，智能语音助手',
    price: 899,
    originalPrice: 1199,
    image: 'https://picsum.photos/id/115/300/300',
    images: [
      'https://picsum.photos/id/115/800/800',
      'https://picsum.photos/id/114/800/800',
      'https://picsum.photos/id/113/800/800'
    ],
    rating: 4.3,
    reviewCount: 156,
    category: '智能家居',
    tags: ['智能', '音质好'],
    brand: 'SmartHome',
    specs: '深空灰',
    stock: 110,
    isNew: false,
    attributes: {
      '扬声器': '360°全向音响',
      '麦克风': '6麦克风阵列',
      '连接': 'WiFi+蓝牙5.0',
      '语音助手': '支持多种语音助手',
      '功率': '30W',
      '尺寸': '直径160mm×高180mm'
    }
  },
  {
    id: 10,
    name: '平板电脑 Pro',
    description: '超清屏幕，强劲性能，便携办公',
    price: 3699,
    originalPrice: 4299,
    image: 'https://picsum.photos/id/177/300/300',
    images: [
      'https://picsum.photos/id/177/800/800',
      'https://picsum.photos/id/178/800/800',
      'https://picsum.photos/id/179/800/800'
    ],
    rating: 4.6,
    reviewCount: 342,
    category: '平板电脑',
    tags: ['办公', '学习'],
    brand: 'TechBrand',
    specs: '银色 / 256GB / WiFi',
    stock: 64,
    isNew: false,
    attributes: {
      '屏幕': '11英寸液晶显示屏',
      '处理器': 'A14仿生芯片',
      '存储': '256GB',
      '前置摄像头': '700万像素',
      '后置摄像头': '1200万像素',
      '电池续航': '最长10小时'
    }
  },
  {
    id: 11,
    name: '5G手机 青春版',
    description: '5G速度，高性价比，轻松游戏',
    price: 1999,
    originalPrice: 2399,
    image: 'https://picsum.photos/id/161/300/300',
    images: [
      'https://picsum.photos/id/161/800/800',
      'https://picsum.photos/id/162/800/800',
      'https://picsum.photos/id/163/800/800'
    ],
    rating: 4.5,
    reviewCount: 487,
    category: '手机数码',
    tags: ['5G', '性价比'],
    brand: 'MobiTech',
    specs: '青春绿 / 128GB',
    stock: 152,
    isNew: false,
    attributes: {
      '屏幕': '6.5英寸LCD',
      '处理器': 'Dimensity 1000+',
      '电池': '5000mAh',
      '摄像头': '主摄4800万+广角800万+微距200万',
      '充电': '33W快充',
      '系统': 'Android 12'
    }
  },
  {
    id: 12,
    name: '无线蓝牙耳机 Mini',
    description: '小巧便携，长续航，高音质',
    price: 799,
    originalPrice: 999,
    image: 'https://picsum.photos/id/114/300/300',
    images: [
      'https://picsum.photos/id/114/800/800',
      'https://picsum.photos/id/115/800/800',
      'https://picsum.photos/id/113/800/800'
    ],
    rating: 4.4,
    reviewCount: 612,
    category: '手机数码',
    tags: ['便携', '热卖'],
    brand: 'SoundMaster',
    specs: '珍珠白',
    stock: 230,
    isNew: false,
    attributes: {
      '续航时间': '单次5小时，充电盒25小时',
      '蓝牙版本': '5.2',
      '防水等级': 'IPX5',
      '重量': '4.5g/只',
      '降噪': '支持通话降噪',
      '触控': '支持触控操作'
    }
  }
];

// 模拟分类数据
export const categories = [
  { id: 1, name: '手机数码', icon: 'PhoneFilled', count: 312 },
  { id: 2, name: '电脑办公', icon: 'Monitor', count: 128 },
  { id: 3, name: '智能穿戴', icon: 'Watch', count: 93 },
  { id: 4, name: '摄影摄像', icon: 'Camera', count: 68 },
  { id: 5, name: '家用电器', icon: 'HomeFilled', count: 157 },
  { id: 6, name: '厨房电器', icon: 'Fridge', count: 112 },
  { id: 7, name: '智能家居', icon: 'SetUp', count: 76 },
  { id: 8, name: '平板电脑', icon: 'IPad', count: 46 }
];

// 模拟品牌数据
export const brands = [
  { id: 1, name: 'TechBrand', logo: 'https://picsum.photos/id/130/100/100', count: 128 },
  { id: 2, name: 'MobiTech', logo: 'https://picsum.photos/id/131/100/100', count: 95 },
  { id: 3, name: 'SoundMaster', logo: 'https://picsum.photos/id/132/100/100', count: 67 },
  { id: 4, name: 'OptiMax', logo: 'https://picsum.photos/id/133/100/100', count: 42 },
  { id: 5, name: 'SmartHome', logo: 'https://picsum.photos/id/134/100/100', count: 86 },
  { id: 6, name: 'ChefMaster', logo: 'https://picsum.photos/id/135/100/100', count: 51 }
];

// 模拟首页广告轮播数据
export const banners = [
  {
    id: 1,
    title: '新品发布',
    description: '最新智能手机全球首发',
    image: 'https://picsum.photos/id/160/1200/400',
    link: '/product/1'
  },
  {
    id: 2,
    title: '夏季特惠',
    description: '精选数码产品低至5折',
    image: 'https://picsum.photos/id/119/1200/400',
    link: '/promotions'
  },
  {
    id: 3,
    title: '智能生活',
    description: '智能家居产品联合促销',
    image: 'https://picsum.photos/id/118/1200/400',
    link: '/categories/7'
  },
  {
    id: 4,
    title: '教育优惠',
    description: '学生专属优惠，笔记本电脑直降1000元',
    image: 'https://picsum.photos/id/180/1200/400',
    link: '/product/2'
  }
];

// 搜索商品方法
export const searchProducts = (keyword) => {
  if (!keyword) return products;
  
  keyword = keyword.toLowerCase();
  return products.filter(product => {
    return product.name.toLowerCase().includes(keyword) || 
           product.description.toLowerCase().includes(keyword) ||
           product.category.toLowerCase().includes(keyword) ||
           (product.brand && product.brand.toLowerCase().includes(keyword));
  });
}; 