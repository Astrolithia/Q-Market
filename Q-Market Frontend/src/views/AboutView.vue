<template>
  <div class="about-page">
    <div class="container">
      <div class="about-header">
        <h1 class="page-title">关于我们</h1>
        <p class="about-subtitle">Q-Market，您的首选购物平台</p>
      </div>
      
      <div class="about-content">
        <div class="about-section">
          <h2 class="section-title">公司简介</h2>
          <p>Q-Market成立于2020年，是一家专注于为消费者提供高品质商品和优质购物体验的电子商务平台。我们秉承"品质、便捷、可靠"的服务理念，致力于打造一个集商品展示、交易、物流、支付、售后于一体的综合性电商平台。</p>
          <p>经过几年的发展，Q-Market已经成为消费者信赖的在线购物目的地，拥有数百万用户和丰富的商品品类，覆盖电子产品、服装鞋帽、家居生活、美妆个护、食品饮料等多个领域。</p>
        </div>
        
        <div class="about-section">
          <h2 class="section-title">企业使命</h2>
          <p>通过科技创新和优质服务，让每个人都能便捷地获取优质商品，享受美好生活。</p>
        </div>
        
        <div class="about-section">
          <h2 class="section-title">企业愿景</h2>
          <p>成为最受消费者喜爱和信赖的在线购物平台，引领电子商务行业的发展方向。</p>
        </div>
        
        <div class="about-section">
          <h2 class="section-title">核心价值观</h2>
          <div class="values-grid">
            <div class="value-item">
              <el-icon><StarFilled /></el-icon>
              <h3>品质至上</h3>
              <p>严选优质商品，确保每件商品都值得信赖</p>
            </div>
            <div class="value-item">
              <el-icon><UserFilled /></el-icon>
              <h3>用户为本</h3>
              <p>以用户需求为中心，提供优质的购物体验</p>
            </div>
            <div class="value-item">
              <el-icon><SuitcaseLine /></el-icon>
              <h3>诚信经营</h3>
              <p>诚实守信，保障消费者权益</p>
            </div>
            <div class="value-item">
              <el-icon><Opportunity /></el-icon>
              <h3>创新驱动</h3>
              <p>不断创新技术和服务，引领行业发展</p>
            </div>
          </div>
        </div>
        
        <div class="about-section">
          <h2 class="section-title">我们的服务</h2>
          <div class="services-list">
            <div class="service-item">
              <el-icon><GoodsFilled /></el-icon>
              <div class="service-content">
                <h3>正品保障</h3>
                <p>所有商品均经过严格筛选，确保正品，假一赔十</p>
              </div>
            </div>
            <div class="service-item">
              <el-icon><Van /></el-icon>
              <div class="service-content">
                <h3>极速配送</h3>
                <p>全国大部分地区支持24小时内送达</p>
              </div>
            </div>
            <div class="service-item">
              <el-icon><Refrigerator /></el-icon>
              <div class="service-content">
                <h3>无忧退换</h3>
                <p>7天内无理由退换，15天内质量问题免费退换</p>
              </div>
            </div>
            <div class="service-item">
              <el-icon><Service /></el-icon>
              <div class="service-content">
                <h3>贴心服务</h3>
                <p>专业客服团队，7×24小时在线解答您的问题</p>
              </div>
            </div>
          </div>
        </div>
        
        <div class="about-section">
          <h2 class="section-title">联系我们</h2>
          <div class="contact-info">
            <div class="contact-item">
              <el-icon><Location /></el-icon>
              <span>总部地址：广东省深圳市南山区科技园南区A栋15楼</span>
            </div>
            <div class="contact-item">
              <el-icon><Phone /></el-icon>
              <span>客服热线：400-123-4567 (周一至周日 8:00-22:00)</span>
            </div>
            <div class="contact-item">
              <el-icon><Message /></el-icon>
              <span>电子邮箱：service@qmarket.com</span>
            </div>
          </div>
          
          <div class="contact-form">
            <h3>留言反馈</h3>
            <el-form>
              <el-form-item>
                <el-input placeholder="您的姓名"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input placeholder="您的邮箱"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input placeholder="您的电话"></el-input>
              </el-form-item>
              <el-form-item>
                <el-input type="textarea" :rows="4" placeholder="请输入您的留言内容"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">提交留言</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.about-page {
  padding: $spacing-xl 0;
}

.about-header {
  text-align: center;
  margin-bottom: $spacing-xl;
  
  .page-title {
    font-size: 32px;
    font-weight: 700;
    margin-bottom: $spacing-md;
    color: $text-color;
  }
  
  .about-subtitle {
    font-size: 18px;
    color: $text-light;
  }
}

.about-content {
  max-width: 900px;
  margin: 0 auto;
}

.about-section {
  background-color: $bg-white;
  border-radius: $border-radius;
  padding: $spacing-xl;
  margin-bottom: $spacing-xl;
  box-shadow: $box-shadow;
  
  .section-title {
    font-size: 24px;
    font-weight: 600;
    margin-bottom: $spacing-lg;
    color: $text-color;
    padding-bottom: $spacing-sm;
    border-bottom: 2px solid $primary-color;
    display: inline-block;
  }
  
  p {
    font-size: 16px;
    line-height: 1.6;
    margin-bottom: $spacing-md;
    color: $text-light;
    
    &:last-child {
      margin-bottom: 0;
    }
  }
}

.values-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: $spacing-lg;
  
  @media (max-width: $breakpoint-sm) {
    grid-template-columns: 1fr;
  }
  
  .value-item {
    background-color: $bg-color;
    border-radius: $border-radius;
    padding: $spacing-lg;
    text-align: center;
    transition: all $transition-time;
    
    &:hover {
      transform: translateY(-5px);
      box-shadow: $box-shadow-hover;
      
      .el-icon {
        color: $primary-color;
        transform: scale(1.2);
      }
    }
    
    .el-icon {
      font-size: 36px;
      color: $text-light;
      margin-bottom: $spacing-md;
      transition: all $transition-time;
    }
    
    h3 {
      font-size: 18px;
      font-weight: 600;
      margin-bottom: $spacing-sm;
      color: $text-color;
    }
    
    p {
      font-size: 14px;
      color: $text-light;
      margin-bottom: 0;
    }
  }
}

.services-list {
  display: flex;
  flex-direction: column;
  gap: $spacing-md;
  
  .service-item {
    display: flex;
    align-items: center;
    background-color: $bg-color;
    border-radius: $border-radius;
    padding: $spacing-md;
    transition: all $transition-time;
    
    &:hover {
      transform: translateX(5px);
      box-shadow: $box-shadow-hover;
      
      .el-icon {
        color: $primary-color;
        transform: scale(1.2);
      }
    }
    
    .el-icon {
      font-size: 24px;
      margin-right: $spacing-md;
      color: $text-light;
      transition: all $transition-time;
    }
    
    .service-content {
      h3 {
        font-size: 16px;
        font-weight: 600;
        margin-bottom: $spacing-xs;
        color: $text-color;
      }
      
      p {
        font-size: 14px;
        color: $text-light;
        margin-bottom: 0;
      }
    }
  }
}

.contact-info {
  margin-bottom: $spacing-xl;
  
  .contact-item {
    display: flex;
    align-items: center;
    margin-bottom: $spacing-md;
    
    .el-icon {
      font-size: 20px;
      margin-right: $spacing-md;
      color: $primary-color;
    }
    
    span {
      font-size: 16px;
      color: $text-light;
    }
  }
}

.contact-form {
  background-color: $bg-color;
  border-radius: $border-radius;
  padding: $spacing-lg;
  
  h3 {
    font-size: 18px;
    font-weight: 600;
    margin-bottom: $spacing-md;
    color: $text-color;
  }
}
</style> 