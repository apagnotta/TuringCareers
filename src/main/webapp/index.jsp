<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Hello, world!</title>
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <meta name="description" content="" />
    <link rel="stylesheet" type="text/css" href="css/commons.css"/>
    <link rel="stylesheet" type="text/css" href="css/navbar.css"/>
</head>
<body>
<div id="mb-nav-wrap" class="navbar">
    <div id="mb-nav" class="grid-3fr">
        <div class="mb-nav-button grid-center">
            <i class="bi bi-search bi-icon-dev hover-dissolve"></i>
        </div>
        <div class="mb-nav-button grid-center">
            <img class="tc-logo" src="static/tc_logo.svg" alt=""/>
        </div>
        <div class="mb-nav-button grid-center">
            <i class="bi bi-list bi-icon-dev hover-dissolve"></i>
            <input type="checkbox" id="mb-nav-hamburger-toggle"/>
            <div id="mb-nav-menu" class="display-none">
                <div id="mb-nav-menu-btn-wrapper">
                    <div class="mb-nav-menu-button">
                        <i class="bi bi-person-circle mb-account-icon bi-icon-dev"></i>
                        <p class="inter-bold">Profile</p>
                    </div>
                    <div class="mb-nav-menu-button">
                        <i class="bi bi-chat-left-text-fill dsk-account-icon bi-icon-dev"></i>
                        <p class="inter-bold">Inbox</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dsk-nav-wrap" class="navbar">
    <div id="dsk-nav" class="grid-3fr">
        <div class="dsk-nav-button grid-center">
            <div id="dsk-nav-search-bar">
                <i class="bi bi-search search-icon"></i>
                <input id="dsk-search-input" type="text" class="inter-regular"/>
            </div>
        </div>
        <div class="dsk-nav-button grid-center">
            <img class="tc-logo" src="static/tc_logo.svg" alt=""/>
        </div>
        <div class="dsk-nav-button dsk-account-section">
            <div class="grid-center">
                <div class="dsk-nav-btn">
                    <i class="bi bi-chat-left-text-fill dsk-account-icon bi-icon-dev hover-dissolve"></i>
                    <p class="inter-bold">Inbox</p>
                </div>
            </div>
            <div class="grid-center">
                <div id="dsk-nav-account-button" class="dsk-nav-btn">
                    <i class="bi bi-person-circle dsk-account-icon bi-icon-dev"></i>
                    <p class="inter-bold">Profile</p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" crossorigin="anonymous"></script>
<script src="js/navbar.js"></script>
</body>
</html>

<div class="dsk-nav-button dsk-account-section">
    <div class="grid-center">
        <div class="dsk-nav-btn">
            <i class="bi bi-chat-left-text-fill dsk-account-icon bi-icon-dev hover-dissolve"></i>
            <p class="inter-bold">Inbox</p>
        </div>
    </div>
    <div class="grid-center">
        <div id="dsk-nav-account-button" class="dsk-nav-btn">
            <i class="bi bi-person-circle dsk-account-icon bi-icon-dev"></i>
            <p class="inter-bold">Profile</p>
        </div>
    </div>
</div>