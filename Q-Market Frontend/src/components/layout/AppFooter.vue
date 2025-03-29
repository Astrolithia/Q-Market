<template>
  <footer class="app-footer">
    <div class="container">
      <div class="footer-content">
        <div class="footer-section">
          <h3 class="footer-title">关于我们</h3>
          <ul class="footer-links">
            <li><a href="#">公司简介</a></li>
            <li><a href="#">加入我们</a></li>
            <li><a href="#">联系方式</a></li>
            <li><a href="#">隐私政策</a></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h3 class="footer-title">购物指南</h3>
          <ul class="footer-links">
            <li><a href="#">购物流程</a></li>
            <li><a href="#">会员介绍</a></li>
            <li><a href="#">常见问题</a></li>
            <li><a href="#">联系客服</a></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h3 class="footer-title">配送方式</h3>
          <ul class="footer-links">
            <li><a href="#">上门自提</a></li>
            <li><a href="#">快递配送</a></li>
            <li><a href="#">配送费用</a></li>
            <li><a href="#">配送范围</a></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h3 class="footer-title">支付方式</h3>
          <ul class="footer-links">
            <li><a href="#">在线支付</a></li>
            <li><a href="#">银行转账</a></li>
            <li><a href="#">货到付款</a></li>
            <li><a href="#">发票制度</a></li>
          </ul>
        </div>
        
        <div class="footer-section">
          <h3 class="footer-title">关注我们</h3>
          <div class="social-icons">
            <a href="#" class="social-icon"><el-icon><Location /></el-icon></a>
            <a href="#" class="social-icon"><el-icon><ChatDotRound /></el-icon></a>
            <a href="#" class="social-icon"><el-icon><Message /></el-icon></a>
            <a href="#" class="social-icon"><el-icon><Service /></el-icon></a>
          </div>
          <div class="qr-code">
            <img src="https://via.placeholder.com/100" alt="微信公众号" />
            <p>扫码关注公众号</p>
          </div>
        </div>
      </div>
      
      <div class="footer-bottom">
        <p>&copy; 2025 Q-Market. All Rights Reserved.</p>
      </div>
    </div>
  </footer>
</template>

<style lang="scss" scoped>
@import '@/assets/css/variables.scss';

.app-footer {
  background-color: #f5f5f5;
  padding: $spacing-xl 0;
  margin-top: $spacing-xl;
}

.footer-content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: $spacing-xl;
}

.footer-section {
  flex: 1;
  min-width: 200px;
  
  @media (max-width: $breakpoint-md) {
    flex-basis: 45%;
  }
  
  @media (max-width: $breakpoint-sm) {
    flex-basis: 100%;
  }
}

.footer-title {
  font-size: 16px;
  font-weight: 600;
  margin-bottom: $spacing-md;
  color: $text-color;
}

.footer-links {
  list-style: none;
  padding: 0;
  
  li {
    margin-bottom: $spacing-sm;
    
    a {
      color: $text-light;
      text-decoration: none;
      transition: color $transition-time;
      
      &:hover {
        color: $primary-color;
      }
    }
  }
}

.social-icons {
  display: flex;
  gap: $spacing-md;
  margin-bottom: $spacing-md;
  
  .social-icon {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 40px;
    height: 40px;
    border-radius: 50%;
    background-color: #f0f0f0;
    color: $text-light;
    transition: all $transition-time;
    
    &:hover {
      background-color: $primary-color;
      color: white;
      transform: translateY(-3px);
    }
  }
}

.qr-code {
  text-align: center;
  
  img {
    width: 100px;
    height: 100px;
    margin-bottom: $spacing-sm;
  }
  
  p {
    font-size: 12px;
    color: $text-lighter;
  }
}

.footer-bottom {
  margin-top: $spacing-xl;
  padding-top: $spacing-lg;
  border-top: 1px solid $border-light;
  text-align: center;
  color: $text-lighter;
  font-size: 12px;
}
</style> 