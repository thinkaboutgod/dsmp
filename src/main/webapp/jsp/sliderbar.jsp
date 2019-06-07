<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">主导航</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>仪表盘</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="bmain.jsp"><i class="fa fa-circle-o"></i> 仪表盘 v1</a></li>
            <li><a href="bmain02.jsp"><i class="fa fa-circle-o"></i> 仪表盘 v2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>布局选项</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="bmain.jsp"><i class="fa fa-circle-o"></i>顶部导航</a></li>
            <li><a href="../layout/boxed.html"><i class="fa fa-circle-o"></i> 盒子</a></li>
            <li><a href="../layout/fixed.html"><i class="fa fa-circle-o"></i> 定位</a></li>
            <li><a href="../layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> 可折叠侧边栏</a></li>
          </ul>
        </li>
        <li>
          <a href="../widgets.html">
            <i class="fa fa-th"></i> <span>小部件</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>图表</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../charts/chartjs.html"><i class="fa fa-circle-o"></i> JS图表</a></li>
            <li><a href="../charts/morris.html"><i class="fa fa-circle-o"></i> Morris图表</a></li>
            <li><a href="../charts/flot.html"><i class="fa fa-circle-o"></i> Flot图表</a></li>
            <li><a href="../charts/inline.html"><i class="fa fa-circle-o"></i> 线性图表</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>UI 元素</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../UI/general.html"><i class="fa fa-circle-o"></i> 常规</a></li>
            <li><a href="../UI/icons.html"><i class="fa fa-circle-o"></i> 图标</a></li>
            <li><a href="../UI/buttons.html"><i class="fa fa-circle-o"></i> 按钮</a></li>
            <li><a href="../UI/sliders.html"><i class="fa fa-circle-o"></i> 滑动</a></li>
            <li><a href="../UI/timeline.html"><i class="fa fa-circle-o"></i> 时间轴</a></li>
            <li><a href="../UI/modals.html"><i class="fa fa-circle-o"></i> 模块</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>表单</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../forms/general.html"><i class="fa fa-circle-o"></i> 常规元素</a></li>
            <li><a href="../forms/advanced.html"><i class="fa fa-circle-o"></i> 高级元素</a></li>
            <li><a href="../forms/editors.html"><i class="fa fa-circle-o"></i> 编辑</a></li>
          </ul>
        </li>
        <li class="treeview active">
          <a href="#">
            <i class="fa fa-table"></i> <span>表格</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="simple.html"><i class="fa fa-circle-o"></i> 简单表格</a></li>
            <li><a href="data.html"><i class="fa fa-circle-o"></i> 数据表格</a></li>
          </ul>
        </li>
        <li>
          <a href="../calendar.html">
            <i class="fa fa-calendar"></i> <span>日历</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="../mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>邮箱</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-folder"></i> <span>样例</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../examples/invoice.html"><i class="fa fa-circle-o"></i> 发票单据</a></li>
            <li><a href="../examples/profile.html"><i class="fa fa-circle-o"></i> 设置</a></li>
            <li><a href="../examples/login.html"><i class="fa fa-circle-o"></i> 登陆</a></li>
            <li><a href="../examples/register.html"><i class="fa fa-circle-o"></i> 注册</a></li>
            <li><a href="../examples/lockscreen.html"><i class="fa fa-circle-o"></i> 锁屏</a></li>
            <li><a href="../examples/404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
            <li><a href="../examples/500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
            <li><a href="../examples/blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
            <li><a href="../examples/pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-share"></i> <span>多级菜单</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> 一级菜单</a></li>
            <li>
              <a href="#"><i class="fa fa-circle-o"></i> 一级菜单
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> 二级菜单</a></li>
                <li>
                  <a href="#"><i class="fa fa-circle-o"></i> 二级菜单
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> 三级菜单</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> 三级菜单</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> 一级菜单</a></li>
          </ul>
        </li>
        <li><a href="../../documentation/index.html"><i class="fa fa-book"></i> <span>文档</span></a></li>
        <li class="header">标签</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>重要</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>警告</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>消息</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>